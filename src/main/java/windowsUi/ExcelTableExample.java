package windowsUi;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelTableExample {
    private static JComboBox<String> columnNameDropdown;
    private static Map<String, Object> editedValues = new HashMap<>();

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Excel Table Example");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            DefaultTableModel tableModel = new DefaultTableModel();

            JTable table = new JTable(tableModel);
            JScrollPane scrollPane = new JScrollPane(table);
            frame.add(scrollPane);
            // Dropdown for selecting column name
            columnNameDropdown = new JComboBox<>();
            //updateColumnNameDropdown(tableModel);
            // Button panel at the bottom
            JPanel buttonPanel = new JPanel();
            buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
            JButton printButton = new JButton("Process");
            printButton.addActionListener(e -> {
                // Save edited values to the model
                //saveEditedValues(tableModel);

               // Print the data from the table model
                printData(tableModel);
            });

            frame.add(columnNameDropdown, BorderLayout.NORTH);
            JButton nullifyButton = new JButton("Edit Cells");
            nullifyButton.addActionListener(e -> {
                // Nullify the values in the selected column
                nullifyColumn(tableModel, columnNameDropdown.getSelectedIndex());
            });

            JButton refreshButton = new JButton("Refresh");
            refreshButton.addActionListener(e -> {
                // Refresh the table with original data
                tableModel.setRowCount(0); // Clear existing data
                try {
                    // Pass the file path to readExcelData method
                    String excelFilePath = "ExcelComparison.xlsx"; // Relative path to the Excel file in the project directory
                    readExcelData(excelFilePath, tableModel);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            });

            buttonPanel.add(printButton);
            buttonPanel.add(refreshButton);
            buttonPanel.add(columnNameDropdown);
            buttonPanel.add(nullifyButton);
            frame.add(buttonPanel, BorderLayout.SOUTH);

            try {
                // Pass the file path to readExcelData method
                String excelFilePath = "ExcelComparison.xlsx"; // Relative path to the Excel file in the project directory
                readExcelData(excelFilePath, tableModel);
            } catch (IOException ex) {
                ex.printStackTrace();
            }

            frame.setSize(400, 300);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }

    private static void readExcelData(String excelFilePath, DefaultTableModel tableModel) throws IOException {
        try (FileInputStream fis = new FileInputStream(excelFilePath);
             Workbook workbook = new XSSFWorkbook(fis)) {

            // Assuming there is only one sheet in the workbook
            Sheet sheet = workbook.getSheetAt(0);
            Row headerRow = sheet.getRow(0);
            // Read column names only if the table model is empty
            if (tableModel.getColumnCount() == 0) {

                for (int i = 0; i < headerRow.getLastCellNum(); i++) {
                    tableModel.addColumn(headerRow.getCell(i).getStringCellValue());
                }

                // Update the column name dropdown after reading the data
                updateColumnNameDropdown(tableModel);
            }

//            // Read column names from the first row
//            Row headerRow = sheet.getRow(0);
//            for (int i = 0; i < headerRow.getLastCellNum(); i++) {
//                tableModel.addColumn(headerRow.getCell(i).getStringCellValue());
//            }

            // Read data rows starting from the second row
            Iterator<Row> rowIterator = sheet.iterator();
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();

                if (row.getRowNum() == 0) {
                    // Skip the header row
                    continue;
                }

                // Add data to the table model
                Object[] rowData = new Object[headerRow.getLastCellNum()];
                for (int i = 0; i < headerRow.getLastCellNum(); i++) {
                    Cell cell = row.getCell(i, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                    rowData[i] = cell.getStringCellValue();
                }
                tableModel.addRow(rowData);
            }
            // Update the column name dropdown after reading the data
            //updateColumnNameDropdown(tableModel);
        }
    }

    private static void saveEditedValues(DefaultTableModel tableModel) {
        for (int i = 0; i < tableModel.getRowCount(); i++) {
            for (int j = 0; j < tableModel.getColumnCount(); j++) {
                String key = i + "-" + j;
                Object editedValue = editedValues.get(key);
                if (editedValue != null) {
                    tableModel.setValueAt(editedValue, i, j);
                }
            }
        }
    }

    private static void printData(DefaultTableModel tableModel) {
        // Print the data from the table model
        for (int i = 0; i < tableModel.getRowCount(); i++) {
            for (int j = 0; j < tableModel.getColumnCount(); j++) {
                System.out.print(tableModel.getValueAt(i, j) + "\t");
            }
            System.out.println();
        }
    }

    private static void nullifyColumn(DefaultTableModel tableModel, int columnIndex) {
        // Prompt user for a new value
        String userInput = JOptionPane.showInputDialog("Enter a new value:");

        if(userInput==null){
            return;
        }
        // Update the selected column with the user input
        for (int i = 0; i < tableModel.getRowCount(); i++) {
            tableModel.setValueAt(userInput, i, columnIndex);
        }

    }

    private static void updateColumnNameDropdown(DefaultTableModel tableModel) {
        // Clear the existing items
        columnNameDropdown.removeAllItems();

        // Add column names to the dropdown
        for (int i = 0; i < tableModel.getColumnCount(); i++) {
            columnNameDropdown.addItem(tableModel.getColumnName(i));
        }

    }

}
