package nuca.frabrienvaf.util;

import java.awt.Color;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;


import nuca.fabrienvaf.model.Material;
import nuca.fabrienvaf.model.Producto;
import nuca.fabrienvaf.model.TipoPalet;
import nuca.fabrienvaf.model.TipoProducto;
import nuca.fabrienvaf.model.Usuario;
import nuca.fabrienvaf.service.MaterialService;
import nuca.fabrienvaf.service.ProductoService;
import nuca.fabrienvaf.service.TipoPaletService;
import nuca.fabrienvaf.service.TipoProductoService;
import nuca.fabrienvaf.service.UsuarioService;

@Component("productos")
public class ListaProductosPDF extends AbstractPdfView{

	
	
	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
			
		@SuppressWarnings("unchecked")
		List<Producto> listadoProductos = (List<Producto>) model.get("productoList");
		//Tamaños y colores de cada seccion
		Font fuenteTitulo = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 16,Color.BLUE);
		Font fuenteTituloColumnas = FontFactory.getFont(FontFactory.HELVETICA_BOLD,12,Color.BLUE);
		Font fuenteDataCeldas = FontFactory.getFont(FontFactory.COURIER, 10, Color.BLACK);

		
		document.setPageSize(PageSize.LETTER.rotate());
		document.setMargins(-20, -20, 40, 20);
		document.open();
		PdfPCell celda = null;
		
		//Tabla para el titulo del PDF
		PdfPTable tablaTitulo = new PdfPTable(1);
		
				
		celda = new PdfPCell(new Phrase("Listado de Clientes", fuenteTitulo));
		celda.setBorder(0);
		celda.setBackgroundColor(new Color(40,190,138));
		celda.setHorizontalAlignment(Element.ALIGN_CENTER);
		celda.setVerticalAlignment(Element.ALIGN_CENTER);
		celda.setPadding(20);
		
		tablaTitulo.addCell(celda);
		tablaTitulo.setSpacingAfter(30);
		
		//Tabla para mostrar listado de cliente
		PdfPTable tablaClientes = new PdfPTable(6);
		tablaClientes.setWidths(new float[] {0.8f, 2f, 2f, 3.5f, 1.5f, 1.5f, 1.5f});
		
		celda = new PdfPCell(new Phrase("ID", fuenteTituloColumnas));
		celda.setBackgroundColor(Color.lightGray);
		celda.setHorizontalAlignment(Element.ALIGN_CENTER);
		celda.setVerticalAlignment(Element.ALIGN_CENTER);
		celda.setPadding(10);
		tablaClientes.addCell(celda);
		
		celda = new PdfPCell(new Phrase("Nombre", fuenteTituloColumnas));
		celda.setBackgroundColor(Color.lightGray);
		celda.setHorizontalAlignment(Element.ALIGN_CENTER);
		celda.setVerticalAlignment(Element.ALIGN_CENTER);
		celda.setPadding(10);
		tablaClientes.addCell(celda);
		
		celda = new PdfPCell(new Phrase("Cantidad Palets", fuenteTituloColumnas));
		celda.setBackgroundColor(Color.lightGray);
		celda.setHorizontalAlignment(Element.ALIGN_CENTER);
		celda.setVerticalAlignment(Element.ALIGN_CENTER);
		celda.setPadding(10);
		tablaClientes.addCell(celda);
		
		celda = new PdfPCell(new Phrase("Cantidad Botes", fuenteTituloColumnas));
		celda.setBackgroundColor(Color.lightGray);
		celda.setHorizontalAlignment(Element.ALIGN_CENTER);
		celda.setVerticalAlignment(Element.ALIGN_CENTER);
		celda.setPadding(10);
		tablaClientes.addCell(celda);
		
		celda = new PdfPCell(new Phrase("Tipo de Palet", fuenteTituloColumnas));
		celda.setBackgroundColor(Color.lightGray);
		celda.setHorizontalAlignment(Element.ALIGN_CENTER);
		celda.setVerticalAlignment(Element.ALIGN_CENTER);
		celda.setPadding(10);
		tablaClientes.addCell(celda);
		
		celda = new PdfPCell(new Phrase("Tipo de Producto", fuenteTituloColumnas));
		celda.setBackgroundColor(Color.lightGray);
		celda.setHorizontalAlignment(Element.ALIGN_CENTER);
		celda.setVerticalAlignment(Element.ALIGN_CENTER);
		celda.setPadding(10);
		tablaClientes.addCell(celda);
		
		celda = new PdfPCell(new Phrase("Usuario", fuenteTituloColumnas));
		celda.setBackgroundColor(Color.lightGray);
		celda.setHorizontalAlignment(Element.ALIGN_CENTER);
		celda.setVerticalAlignment(Element.ALIGN_CENTER);
		celda.setPadding(10);
		tablaClientes.addCell(celda);
		
		for(Producto producto : listadoProductos) {
			celda = new PdfPCell(new Phrase(producto.getId().toString(), fuenteDataCeldas));
			celda.setPadding(5);
			tablaClientes.addCell(celda);
			
			celda = new PdfPCell(new Phrase(producto.getNombre(), fuenteDataCeldas));
			celda.setPadding(5);
			tablaClientes.addCell(celda);
			
			celda = new PdfPCell(new Phrase(String.valueOf(producto.getCantidadPalets()), fuenteDataCeldas));
			celda.setPadding(5);
			tablaClientes.addCell(celda);
			
			celda = new PdfPCell(new Phrase(String.valueOf(producto.getCantidadBotes()), fuenteDataCeldas));
			celda.setPadding(5);
			tablaClientes.addCell(celda);
			
			celda = new PdfPCell(new Phrase(producto.getTipoPalet().getNombre(), fuenteDataCeldas));
			celda.setPadding(5);
			tablaClientes.addCell(celda);
			
			celda = new PdfPCell(new Phrase(producto.getTipoProducto().getNombre(), fuenteDataCeldas));
			celda.setPadding(5);
			tablaClientes.addCell(celda);
			
			celda = new PdfPCell(new Phrase(producto.getUsuario().getNombre(), fuenteDataCeldas));
			celda.setPadding(5);
			tablaClientes.addCell(celda);
		}		
		
		document.add(tablaTitulo);
		document.add(tablaClientes);
	}
}
