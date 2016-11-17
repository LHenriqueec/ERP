package util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import entity.Produto;

public class XmlUtils {

	private static List<Produto> produtos;

	public static List<Produto> loadProdutoToFile(File file) {
		produtos = new ArrayList<>();
		try {
			Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(file);
			Element nfeTag = (Element) doc.getElementsByTagName("infNFe").item(0);

			NodeList node = nfeTag.getElementsByTagName("det");

			for (int i = 0; i < node.getLength(); i++) {
				Produto produto = new Produto();
				Element prodTag = (Element) node.item(i);

				produto.setCodigo(prodTag.getElementsByTagName("cProd").item(0).getTextContent());
				produto.setNome(prodTag.getElementsByTagName("xProd").item(0).getTextContent());
				produto.setCusto(Double.parseDouble(prodTag.getElementsByTagName("vUnCom").item(0).getTextContent()));

				produtos.add(produto);
			}

		} catch (SAXException | IOException | ParserConfigurationException e) {
			e.printStackTrace();
		}

		return produtos;
	}

	public static List<Produto> getProdutos() {
		return produtos;
	}

	public static NodeList carregarXML(String elementRoot, Path filePath) {
		NodeList nodes = null;
		try (InputStream is = Files.newInputStream(filePath)) {

			Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(is);
			nodes = doc.getElementsByTagName(elementRoot);

		} catch (SAXException | IOException | ParserConfigurationException e) {
			e.printStackTrace();
		}

		return nodes;
	}
	
	public static List<Produto> readProduto(NodeList nodes) {
		produtos = new ArrayList<>();
		Produto p = null;
		for (int i = 0; i < nodes.getLength(); i++) {
			p = new Produto();
			Element node = (Element) nodes.item(i);
			p.setCodigo(node.getAttribute("a_id_produto"));
			p.setNome(node.getAttribute("a_descricao"));
			produtos.add(p);
		}
		
		return produtos;
	}
}









