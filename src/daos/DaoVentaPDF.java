package daos;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import vista.Facturacion;

/**
 *
 * @author barre
 */
public class DaoVentaPDF {

    private String fechaActual = "";
    private String nombreArchivoPDFVenta = "";

    public void generarFacturaPDF() {
        try {
            // Cargar la fecha actual
            Date date = new Date();
            fechaActual = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(date);
            // Cambiar el formato de la fecha de / a _
            String fechaNueva = new SimpleDateFormat("yyyy_MM_dd HH_mm_ss").format(date);

            nombreArchivoPDFVenta = "Venta_" + fechaNueva + ".pdf";

            FileOutputStream archivo;
            File file = new File("src/tickets/" + nombreArchivoPDFVenta);
            archivo = new FileOutputStream(file);

            Document doc = new Document();
            PdfWriter.getInstance(doc, archivo);
            doc.open();

            // Imagen en el lado superior derecho
            Image img = Image.getInstance("src/img/logo.jpg");
            img.scaleToFit(100, 100); // Ajustar tamaño de la imagen

            PdfPTable Encabezado = new PdfPTable(2);
            Encabezado.setWidthPercentage(100);
            Encabezado.setWidths(new float[]{70, 30}); // Proporción de columnas
            Encabezado.getDefaultCell().setBorder(0);

            // Celda vacía para alinear la imagen a la derecha
            Encabezado.addCell("");
            PdfPCell cellImg = new PdfPCell(img);
            cellImg.setBorder(0);
            cellImg.setHorizontalAlignment(Element.ALIGN_RIGHT);
            Encabezado.addCell(cellImg);

            doc.add(Encabezado);

            // Información del encabezado
            PdfPTable infoTabla = new PdfPTable(1);
            infoTabla.setWidthPercentage(100);
            infoTabla.getDefaultCell().setBorder(0);
            Font negrita = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD, BaseColor.BLUE);

            Paragraph info = new Paragraph();
            info.add(new Chunk("Factura: 001\n", negrita));
            info.add("Fecha: " + fechaActual + "\n\n");
            
            String nombre = "Super Oaxaca";
            String telefono = "096765453629";
            String direccion = "Calle Tlaloc";
            
            info.add("NOMBRE: " + nombre + "\n");
            info.add("TELÉFONO: " + telefono + "\n");
            info.add("DIRECCIÓN: " + direccion + "\n");

            PdfPCell cellInfo = new PdfPCell(info);
            cellInfo.setBorder(0);
            infoTabla.addCell(cellInfo);

            doc.add(infoTabla);

            // Espacio en blanco
            Paragraph espacio = new Paragraph();
            espacio.add(Chunk.NEWLINE);
            doc.add(espacio);

            // Tabla de productos
            PdfPTable tablaProducto = new PdfPTable(4);
            tablaProducto.setWidthPercentage(100);
            tablaProducto.setWidths(new float[]{15f, 50f, 15f, 20f}); // Tamaños de columnas

            PdfPCell producto1 = new PdfPCell(new Phrase("Cantidad: ", negrita));
            PdfPCell producto2 = new PdfPCell(new Phrase("Descripción: ", negrita));
            PdfPCell producto3 = new PdfPCell(new Phrase("Precio Unitario: ", negrita));
            PdfPCell producto4 = new PdfPCell(new Phrase("Precio Total: ", negrita));

            // Estilo de encabezados
            BaseColor bgColor = BaseColor.LIGHT_GRAY;
            producto1.setBackgroundColor(bgColor);
            producto2.setBackgroundColor(bgColor);
            producto3.setBackgroundColor(bgColor);
            producto4.setBackgroundColor(bgColor);

            // Sin bordes
            producto1.setBorder(0);
            producto2.setBorder(0);
            producto3.setBorder(0);
            producto4.setBorder(0);

            // Agregar encabezados
            tablaProducto.addCell(producto1);
            tablaProducto.addCell(producto2);
            tablaProducto.addCell(producto3);
            tablaProducto.addCell(producto4);

            // Agregar los productos
            for (int i = 0; i < Facturacion.jTable_productos.getRowCount(); i++) {
                String producto = Facturacion.jTable_productos.getValueAt(i, 1).toString();
                String cantidad = Facturacion.jTable_productos.getValueAt(i, 2).toString();
                String precio = Facturacion.jTable_productos.getValueAt(i, 3).toString();
                String total = Facturacion.jTable_productos.getValueAt(i, 7).toString();

                tablaProducto.addCell(cantidad);
                tablaProducto.addCell(producto);
                tablaProducto.addCell(precio);
                tablaProducto.addCell(total);
            }

            // Agregar la tabla de productos al documento
            doc.add(tablaProducto);

            // Total a pagar
            Paragraph totalPagar = new Paragraph();
            totalPagar.add(Chunk.NEWLINE);
            totalPagar.add("Total a pagar: " + Facturacion.txt_total_pagar.getText());
            totalPagar.setAlignment(Element.ALIGN_RIGHT);
            doc.add(totalPagar);

            // Mensaje de agradecimiento
            Paragraph mensaje = new Paragraph();
            mensaje.add(Chunk.NEWLINE);
            mensaje.add("¡Gracias por su compra!");
            mensaje.setAlignment(Element.ALIGN_CENTER);
            doc.add(mensaje);

            // Cerrar el documento
            doc.close();
            archivo.close();

            // Abrir el archivo generado
            Desktop.getDesktop().open(file);

        } catch (DocumentException | IOException e) {
            System.out.println("Error en: " + e);
        }
    }

}
