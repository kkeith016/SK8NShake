package com.pluralsight.System;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ReceiptSaver {

    public static void saveReceipt(String receiptContent) {
        try {
            String timestamp = LocalDateTime.now()
                    .format(DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss"));
            String filename = "src/main/resources/receipts/" + timestamp + ".txt";

            try (FileWriter writer = new FileWriter(filename)) {
                writer.write(receiptContent);
            }

            System.out.println("Receipt saved successfully → " + filename);

        } catch (IOException e) {
            System.err.println("Error saving receipt: " + e.getMessage());
        }
    }
}
