package util;

import java.nio.file.Paths;
import java.text.NumberFormat;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.DoubleStream;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;

import entity.Faturamento;
import entity.Produto;

public class FaturamentoUtils {

	@SuppressWarnings("unused")
	private static Faturamento readFaturamentoToXML(NamedNodeMap attributes) {
		String value = attributes.getNamedItem("Campo").getTextContent();
		Faturamento faturamento = new Faturamento();
		Produto produto = new Produto();

		String[] newValue = value.split("-");

		String valueDateAndCode = newValue[0];
		valueDateAndCode = valueDateAndCode.startsWith("_") ? valueDateAndCode.replaceFirst("_", "") : valueDateAndCode;
		if(valueDateAndCode.equals("Total") || valueDateAndCode.split("_")[1].equals("Total")) return null;

		String valueDate = valueDateAndCode.split("_")[0];
		String valueCode = valueDateAndCode.split("_")[1];
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate date = LocalDate.parse(valueDate, formatter);
		faturamento.setDataVenda(date);

		String nomeProduto = newValue[1].replaceAll("_", " ");
		produto.setCodigo(valueCode);
		produto.setNome(nomeProduto);

		value = attributes.getNamedItem("Quantidade_Soma").getTextContent();
		faturamento.setQuantidade(Integer.parseInt(value));

		value = attributes.getNamedItem("Total_P.M.V.").getTextContent();
		faturamento.setValorTotal(Double.parseDouble(value));

		double valorUnitario = faturamento.getValorTotal() / faturamento.getQuantidade();
		faturamento.setValorUnitario(valorUnitario);

		faturamento.setProduto(produto);

		return faturamento;
	}

	public static Faturamento readFaturamentoToXMLCompleto(NamedNodeMap attributes) throws ParseException {
		String cfop = attributes.getNamedItem("id_cfop").getTextContent();
		Faturamento faturamento = null;
		
		if (isCfopVenda(cfop)) {
//			Cliente cliente = DaoFactory.getInstance().getClienteDAO()
//					.getClienteByName(attributes.getNamedItem("id_pessoa").getTextContent().split("-")[1]);
			
			Produto produto = new Produto();
			produto.setCodigo(attributes.getNamedItem("id_seq_produto").getTextContent().split("-")[0]);
			produto.setNome(attributes.getNamedItem("id_seq_produto").getTextContent().split("-")[1]);
			
			NumberFormat format = NumberFormat.getInstance();
			
			faturamento = new Faturamento();
			faturamento.setProduto(produto);
			
			String quantidade = attributes.getNamedItem("peso").getTextContent();
			faturamento.setQuantidade(format.parse(quantidade).intValue());
			
			String vr_unitario = attributes.getNamedItem("vr_unitario").getTextContent();
			faturamento.setValorUnitario(format.parse(vr_unitario).doubleValue());
			
			String vr_total = attributes.getNamedItem("vr_total").getTextContent();
			faturamento.setValorTotal(format.parse(vr_total).doubleValue());
			
			faturamento.setDataVenda(LocalDate.parse(attributes.getNamedItem("dt_venda").getTextContent(), DateTimeFormatter.ofPattern("dd/MM/yyyy")));
			
		}
		
		return faturamento;
	}

	public static double readTotal(Month month) {
		List<Faturamento> faturamentos = new ArrayList<>();
		NodeList nodes = XmlUtils.carregarXML("fat113_1", Paths.get("/home/luiz/Documentos/fat113_1.xml"));
		try {
			for (int i = 0; i < nodes.getLength(); i++) {
				Faturamento faturamento = readFaturamentoToXMLCompleto(nodes.item(i).getAttributes());
				if (faturamento != null) {
					faturamentos.add(faturamento);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		double total = faturamentos.stream().filter(fat -> fat.getDataVenda().getMonth().equals(month))
				.flatMapToDouble(fat -> DoubleStream.of(fat.getValorTotal())).sum();

		return total;
	}

	private static boolean isCfopVenda(String cfop) {
		//TODO: fazer a leitura dos CFOPs de venda através de um arquivo .property
		switch (cfop) {
		case "5.102": return true;
		case "5.403": return true;
		case "5.405": return true;
		case "6.102": return true;
		default: return false;
		}
	}
}
