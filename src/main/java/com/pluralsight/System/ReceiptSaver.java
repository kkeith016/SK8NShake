package com.pluralsight.System;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ReceiptSaver {

    /*
    When the customer completes the order, the order details should be saved to a
    receipts folder. Each order should have its own receipt file, and it should be
    named by the date and time that the order was placed
    (yyyyMMdd-hhmmss.txt - i.e. 20230329-121523.txt)
     */

    public static void saveReceipt(String receiptContent) {
        try {
            // Make a timestamped filename
            String timestamp = LocalDateTime.now()
                    .format(DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss"));
            String filename = "src/main/resources/receipts/" + timestamp + ".txt";

            // Write the receipt content to the file
            try (FileWriter writer = new FileWriter(filename)) {
                writer.write(receiptContent);
            }

            System.out.println("Receipt saved successfully → " + filename);

        } catch (IOException e) {
            System.err.println("Error saving receipt: " + e.getMessage());
        }
    }
}