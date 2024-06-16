package website;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

import java.io.File;
public class OCRextraction {
	
	public static void main(String[] args) {
        Tesseract tesseract = new Tesseract();
        tesseract.setDatapath("F:\\Dataproject\\Text\\productlist.png");

        try {
            String text = tesseract.doOCR(new File("F:\\Dataproject\\image"));
            System.out.println(text);
            // Parse text to extract product names and prices
        } catch (TesseractException e) {
            e.printStackTrace();
        }

}}
