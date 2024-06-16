package website;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import net.sourceforge.tess4j.TesseractException;
public class Datacomparison {
	
	 public static void main(String[] args) throws TesseractException, JSONException, IOException  {
	        // Load JSON data
	        FileInputStream fis = new FileInputStream("F:\\Dataproject\\Text\\web_scraped_data.json");
	        JSONArray webData = new JSONArray(new String(fis.readAllBytes()));
	        fis.close();

	        // Assume OCR data is loaded into ocrData JSONArray
	        JSONArray ocrData = new JSONArray();
	        // Add OCR data parsing logic here

	        JSONArray discrepancies = new JSONArray();

	        for (int i = 0; i < webData.length(); i++) {
	            JSONObject webProduct = webData.getJSONObject(i);
	            String webName = webProduct.getString("name");
	            String webPrice = webProduct.getString("price");
	            String webRate = webProduct.getString("rating");

	            

	            for (int j = 0; j < ocrData.length(); j++) {
	                JSONObject ocrProduct = ocrData.getJSONObject(j);
	                String ocrName = ocrProduct.getString("name");
	                String ocrPrice = ocrProduct.getString("price");
	                String ocrRate = ocrProduct.getString("rating");

	                

	                if (webName.equals(ocrName) && !webPrice.equals(ocrPrice)) {
	                    JSONObject discrepancy = new JSONObject();
	                    
	                    discrepancy.put("name", webName);
	                    discrepancy.put("webPrice", webPrice);
	                    discrepancy.put("webRate", webRate);

	                    discrepancy.put("ocrPrice", ocrPrice);
	                    discrepancies.put(discrepancy);
	                }
	            }
	        }

	        try (FileWriter file = new FileWriter("price_discrepancies.json")) {
	            file.write(discrepancies.toString());
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }

}
