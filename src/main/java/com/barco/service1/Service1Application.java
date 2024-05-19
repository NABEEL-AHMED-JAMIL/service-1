package com.barco.service1;

import com.barco.service1.model.pojo.stock.StockPrice;
import com.barco.service1.model.repository.StockPriceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import javax.annotation.PostConstruct;
import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.TimeZone;
import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * @author Nabeel Ahmed
 */
@SpringBootApplication
@ComponentScan(basePackages = { "com.barco.*" })
public class Service1Application {

	public static String QATAR_TIME_ZONE = "Asia/Qatar";
	public final static String FORMAT_DATE = "d-MMMM-yyyy";
	private String downloadUrl = "https://raw.githubusercontent.com/rrohitramsen/firehose/master/src/main/resources/data/stock_6_lac.csv";
	@Autowired
	private StockPriceRepository stockPriceRepository;

	public static void main(String[] args) {
		SpringApplication.run(Service1Application.class, args);
	}

	@PostConstruct
	public void started() {
		// default system timezone for application
		TimeZone.setDefault(TimeZone.getTimeZone(QATAR_TIME_ZONE));
	}

	/**
	 * Note :- Convert the String of Data to StockPrice
	 * Process time is less then 0.ms => done task
	 * */
	public static StockPrice rawDataToPojo(String inputLine) {
		long startTime = System.currentTimeMillis();
		StockPrice stockPrice = new StockPrice();
		try {
			String[] stockPrices = inputLine.split(",");
			if(stockPrices[0] != null && !stockPrices[0].equals("")) {
				stockPrice.setDate(new SimpleDateFormat(FORMAT_DATE, Locale.ENGLISH).parse(stockPrices[0]));
				System.out.println("Date Found :- " + stockPrice.getDate());
			}
			if(stockPrices[1] != null && !stockPrices[1].equals("")) {
				stockPrice.setOpenPrice(Double.valueOf(stockPrices[1]));
			}
			if(stockPrices[2] != null && !stockPrices[2].equals("")) {
				stockPrice.setHighPrice(Double.valueOf(stockPrices[2]));
			}
			if(stockPrices[3] != null && !stockPrices[3].equals("")) {
				stockPrice.setLowPrice(Double.valueOf(stockPrices[3]));
			}
			if(stockPrices[4] != null && !stockPrices[4].equals("")) {
				stockPrice.setClosePrice(Double.valueOf(stockPrices[4]));
			}
			if(stockPrices[5] != null && !stockPrices[5].equals("")) {
				stockPrice.setWap(Double.valueOf(stockPrices[5]));
			}
			if(stockPrices[6] != null &&  !stockPrices[6].equals("")) {
				stockPrice.setNoOfShares(Integer.valueOf(stockPrices[6]));
			}
			if(stockPrices[7] != null && !stockPrices[7].equals("")) {
				stockPrice.setNoOfTrades(Integer.valueOf(stockPrices[7]));
			}
			if(stockPrices[8] != null && !stockPrices[8].equals("")) {
				stockPrice.setTotalTurnover(Double.valueOf(stockPrices[8]));
			}
			if(stockPrices[9] != null && !stockPrices[9].equals("")) {
				stockPrice.setDeliverableQty(Integer.valueOf(stockPrices[9]));
			}
			if(stockPrices[10] != null && !stockPrices[10].equals("")) {
				stockPrice.setDeliQtyToTradedQty(Double.valueOf(stockPrices[10]));
			}
			if(stockPrices[11] != null && !stockPrices[11].equals("")) {
				stockPrice.setSpreadHighLow(Double.valueOf(stockPrices[11]));
			}
			if(stockPrices[12] != null && !stockPrices[12].equals("")) {
				stockPrice.setSpreadCloseOpen(Double.valueOf(stockPrices[12]));
			}
		}  catch (NumberFormatException ex) {
			System.err.println("Error " + ex.getLocalizedMessage());
		} catch (ParseException ex) {
			System.err.println("Error " + ex.getLocalizedMessage());
        }
        System.out.println("Rwa Data   --> StockPrice Convert Time :- " + (System.currentTimeMillis() - startTime) + ".ms");
		return stockPrice;
	}

	/**
	 * Method use to connect to the remote url
	 * and download the stream
	 * */
	public void openConnection() {
		try {
			long startTime = System.currentTimeMillis();
			URL myUrl = new URL(downloadUrl);
			HttpsURLConnection conn = (HttpsURLConnection) myUrl.openConnection();
			InputStream inputStream = conn.getInputStream();
			InputStreamReader isr = new InputStreamReader(inputStream);
			BufferedReader br = new BufferedReader(isr);
			List<StockPrice> stockPricesPojos = new ArrayList<>();
			String inputLine;
			Integer counter = 0;
			Integer currentLine = 0;
			Integer loop = 0;
			while ((inputLine = br.readLine()) != null) {
				System.out.println("Current Line :- " + (currentLine) + " Value :- " + inputLine);
				if(currentLine != 0) {
					System.out.println("+---------------------------------------------------------------------------------------------------------+");
					StockPrice stockPrice = rawDataToPojo(inputLine);
					System.out.println("+---------------------------------------------------------------------------------------------------------+");
					stockPricesPojos.add(stockPrice);
					if(counter == 35000) {
						System.out.println("Loop :- " + loop++  + "  Counter :- " + counter + " Total Fetch :- " + (loop * counter));
						if(stockPricesPojos != null) {
							this.stockPriceRepository.saveAll(stockPricesPojos);
							stockPricesPojos = new ArrayList<>();
						}
						System.out.println("+---------------------------------------------------------------------------------------------------------+");
						counter = 0;
					}
				}
				currentLine++; counter++;
			}
			br.close();
			System.out.println("Start Time :- " + new Date(startTime) + ", Current Time :- " +
				new Date(System.currentTimeMillis()) + "Total Time :- " + (System.currentTimeMillis() - startTime) + ".ms");
		} catch (IOException ex) {
			System.out.println("Error :- " + ex.getLocalizedMessage());
		} catch (NullPointerException ex) {
			System.out.println("Error :- " + ex.getLocalizedMessage());
		}
	}

}
