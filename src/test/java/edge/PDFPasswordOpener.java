/*
package edge;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.encryption.AccessPermission;
import org.apache.pdfbox.pdmodel.encryption.StandardDecryptionMaterial;

import java.io.File;
import java.io.IOException;

public class PDFPasswordOpener {
    public static void main(String[] args) {
        // Path to the password-protected PDF file
        String pdfFilePath = "src/test/java/edge/AccStmt_03414262_082023_5848.pdf";
        String password = "your_password_here";
        boolean unlocked=false;

        int maxDigits = 5; // Number of digits for the numbers (0 to 99999)

        for (int num = 0; num < Math.pow(36, maxDigits) && !unlocked; num++) {
            StringBuilder combination = new StringBuilder();

            // Convert 'num' to a base-36 representation
            int temp = num;
            for (int i = 0; i < maxDigits; i++) {
                int digitValue = temp % 36;
                char digitChar = (digitValue < 10) ? (char) ('0' + digitValue) : (char) ('A' + digitValue - 10);
                combination.insert(0, digitChar);
                temp /= 36;
            }

            System.out.println(combination);
            password = String.valueOf(combination)+5848;
            try {
                File pdfFile = new File(pdfFilePath);
                PDDocument document = PDDocument.load(pdfFile, password);
                // Close the document when you're done
                document.close();
                unlocked=true;
                System.out.println("Unlocked");
            } catch (IOException e) {
                System.out.println("Locked");
            }
        }

    }
}
*/
