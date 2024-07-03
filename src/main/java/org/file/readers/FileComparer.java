package org.file.readers;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.Scanner;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.xssf.usermodel.*;

public class FileComparer extends JFrame {
    private JTable dataTable;
    private DefaultTableModel tableModel;
    private JButton uploadButton;
    private JButton compareButton;
    private JButton exportButton;
    private JFileChooser fileChooser;
    private File[] selectedFiles;
    private JLabel statusLabel;

    public FileComparer() {
        setTitle("File Comparer");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Initialize components
        tableModel = new DefaultTableModel();
        dataTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(dataTable);
        uploadButton = new JButton("Upload Files");
        compareButton = new JButton("Compare Files");
        exportButton = new JButton("Export to Excel");
        fileChooser = new JFileChooser();
        statusLabel = new JLabel("");

        // Add components to the frame
        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(uploadButton);
        buttonPanel.add(compareButton);
        buttonPanel.add(exportButton);
        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
        add(statusLabel, BorderLayout.NORTH);

        // Register action listeners
        uploadButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                selectFiles();
            }
        });

        compareButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                compareFiles();
            }
        });

        exportButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                exportToExcel();
            }
        });

        // Initially disable compare and export buttons
        compareButton.setEnabled(false);
        exportButton.setEnabled(false);

        pack();
        setLocationRelativeTo(null);
    }

    private void selectFiles() {
        fileChooser.setMultiSelectionEnabled(true);
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            selectedFiles = fileChooser.getSelectedFiles();
            if (selectedFiles.length != 2) {
                JOptionPane.showMessageDialog(this, "Please select exactly two files.");
                return;
            }

            // Display data from both files in the UI
            displayData(selectedFiles[0], selectedFiles[1]);

            compareButton.setEnabled(true);
            statusLabel.setText("Files uploaded. Click Compare to proceed.");
        }
    }

//    private void displayData(File file1, File file2) {
//        try (Scanner scanner1 = new Scanner(file1);
//             Scanner scanner2 = new Scanner(file2)) {
//            tableModel.setRowCount(0); // Clear existing rows
//
//            // Read headers
//            String headers1 = scanner1.nextLine();
//            String headers2 = scanner2.nextLine();
//            String[] headerColumns1 = headers1.split("\\s+");
//            String[] headerColumns2 = headers2.split("\\s+");
//
//            // Determine the number of columns
//            int numColumns = Math.max(headerColumns1.length, headerColumns2.length);
//
//            // Add column names
//            Object[] columnNames = new Object[numColumns * 2];
//            for (int i = 0; i < numColumns; i++) {
//                columnNames[i * 2] = "Left_" + (i < headerColumns1.length ? headerColumns1[i] : "");
//                columnNames[i * 2 + 1] = "Right_" + (i < headerColumns2.length ? headerColumns2[i] : "");
//            }
//            tableModel.setColumnIdentifiers(columnNames);
//
//            // Display data from file 1
//            while (scanner1.hasNextLine()) {
//                String line1 = scanner1.nextLine();
//                String[] data1 = line1.split("\\s+", -1); // Preserve trailing empty strings
//                Object[] rowData = new Object[numColumns * 2];
//                for (int i = 0; i < numColumns; i++) {
//                    rowData[i * 2] = (i < data1.length) ? data1[i] : "";
//                }
//                tableModel.addRow(rowData);
//            }
//
//            // Display data from file 2
//            while (scanner2.hasNextLine()) {
//                String line2 = scanner2.nextLine();
//                String[] data2 = line2.split("\\s+", -1); // Preserve trailing empty strings
//                Object[] rowData = new Object[numColumns * 2];
//                for (int i = 0; i < numColumns; i++) {
//                    rowData[i * 2 + 1] = (i < data2.length) ? data2[i] : "";
//                }
//                tableModel.addRow(rowData);
//            }
//        } catch (FileNotFoundException ex) {
//            ex.printStackTrace();
//            statusLabel.setText("Error displaying data.");
//        }
//    }

    private void displayData(File file1, File file2) {
        try (Scanner scanner1 = new Scanner(file1);
             Scanner scanner2 = new Scanner(file2)) {
            tableModel.setRowCount(0); // Clear existing rows

            // Read headers
            String headers1 = scanner1.nextLine();
            String headers2 = scanner2.nextLine();
            String[] headerColumns1 = headers1.split("\t"); // Use '\t' as delimiter for tab-separated files
            String[] headerColumns2 = headers2.split("\t"); // Use '\t' as delimiter for tab-separated files

            // Determine the number of columns
            int numColumns = Math.max(headerColumns1.length, headerColumns2.length);

            // Add column names
            Object[] columnNames = new Object[numColumns * 2];
            for (int i = 0; i < numColumns; i++) {
                columnNames[i * 2] = "Left_" + (i < headerColumns1.length ? headerColumns1[i] : "");
                columnNames[i * 2 + 1] = "Right_" + (i < headerColumns2.length ? headerColumns2[i] : "");
            }
            tableModel.setColumnIdentifiers(columnNames);

            // Display data from file 1
            while (scanner1.hasNextLine()) {
                String line1 = scanner1.nextLine();
                String[] data1 = line1.split("\t", -1); // Use '\t' as delimiter for tab-separated files
                Object[] rowData = new Object[numColumns * 2];
                for (int i = 0; i < numColumns; i++) {
                    rowData[i * 2] = (i < data1.length) ? data1[i] : ""; // Fill missing cells with empty strings
                }
                tableModel.addRow(rowData);
            }

            // Display data from file 2
            while (scanner2.hasNextLine()) {
                String line2 = scanner2.nextLine();
                String[] data2 = line2.split("\t", -1); // Use '\t' as delimiter for tab-separated files
                Object[] rowData = new Object[numColumns * 2];
                for (int i = 0; i < numColumns; i++) {
                    rowData[i * 2 + 1] = (i < data2.length) ? data2[i] : ""; // Fill missing cells with empty strings
                }
                tableModel.addRow(rowData);
            }
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
            statusLabel.setText("Error displaying data.");
        }
    }


    private void compareFiles() {
        if (selectedFiles == null || selectedFiles.length != 2) {
            JOptionPane.showMessageDialog(this, "Please upload two files.");
            return;
        }

        File file1 = selectedFiles[0];
        File file2 = selectedFiles[1];

        try (Scanner scanner1 = new Scanner(file1);
             Scanner scanner2 = new Scanner(file2)) {
            tableModel.setRowCount(0); // Clear existing rows

            // Read headers
            String headers1 = scanner1.nextLine();
            String headers2 = scanner2.nextLine();
            String[] headerColumns1 = headers1.split("\\s+");
            String[] headerColumns2 = headers2.split("\\s+");

            // Determine the number of columns
            int numColumns = Math.max(headerColumns1.length, headerColumns2.length);

            // Add column names
            Object[] columnNames = new Object[numColumns * 2];
            for (int i = 0; i < numColumns; i++) {
                columnNames[i * 2] = "Left_" + (i < headerColumns1.length ? headerColumns1[i] : "");
                columnNames[i * 2 + 1] = "Right_" + (i < headerColumns2.length ? headerColumns2[i] : "");
            }
            tableModel.setColumnIdentifiers(columnNames);

            // Compare data
            while (scanner1.hasNextLine() || scanner2.hasNextLine()) {
                String line1 = scanner1.hasNextLine() ? scanner1.nextLine() : "";
                String line2 = scanner2.hasNextLine() ? scanner2.nextLine() : "";
                String[] data1 = line1.split("\\s+", -1); // Preserve trailing empty strings
                String[] data2 = line2.split("\\s+", -1); // Preserve trailing empty strings

                Object[] rowData = new Object[numColumns * 2];
                for (int i = 0; i < numColumns; i++) {
                    String leftData = (i < data1.length) ? data1[i] : "";
                    String rightData = (i < data2.length) ? data2[i] : "";

                    rowData[i * 2] = leftData;
                    rowData[i * 2 + 1] = rightData;

                    // Highlight differences in red
                    if (!leftData.equals(rightData) && !leftData.isEmpty() && !rightData.isEmpty()) {
                        leftData = "<html><font color='red'>" + leftData + "</font></html>";
                        rightData = "<html><font color='red'>" + rightData + "</font></html>";
                        rowData[i * 2] = leftData;
                        rowData[i * 2 + 1] = rightData;
                    }
                }
                tableModel.addRow(rowData);
            }
            statusLabel.setText("Files compared successfully.");
            exportButton.setEnabled(true);
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
            statusLabel.setText("Error comparing files.");
        }
    }

    private void exportToExcel() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Save As");
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.setFileFilter(new FileNameExtensionFilter("Excel Files", "xlsx"));

        int userSelection = fileChooser.showSaveDialog(this);
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();
            if (!fileToSave.getName().endsWith(".xlsx")) {
                fileToSave = new File(fileToSave.getAbsolutePath() + ".xlsx");
            }

            try (Workbook workbook = new XSSFWorkbook()) {
                Sheet sheet = workbook.createSheet("Comparison");

                // Copy headers from table to Excel sheet
                Row headerRow = sheet.createRow(0);
                for (int j = 0; j < tableModel.getColumnCount(); j++) {
                    headerRow.createCell(j).setCellValue(tableModel.getColumnName(j));
                }

                // Copy data from table to Excel sheet
                for (int i = 0; i < tableModel.getRowCount(); i++) {
                    Row row = sheet.createRow(i + 1); // Start from row 1 to skip headers
                    for (int j = 0; j < tableModel.getColumnCount(); j++) {
                        Cell cell = row.createCell(j);
                        Object value = tableModel.getValueAt(i, j);
                        if (value != null) {
                            String text = value.toString();
                            if (text.startsWith("<html><font color='red'>")) {
                                // Extract the numerical value between font tags
                                int startIndex = text.indexOf('>') + 1;
                                int endIndex = text.lastIndexOf('<');
                                String numericValue = text.substring(startIndex, endIndex);
                                /// Remove non-numeric characters
                                numericValue = numericValue.replaceAll("[^0-9.]", "");

                                 // Convert the extracted value to double
                                double numericDouble = Double.parseDouble(numericValue);
                                cell.setCellValue(numericDouble);
                                // Apply red color formatting to the cell
                                Font font = workbook.createFont();
                                font.setColor(IndexedColors.RED.getIndex());
                                CellStyle style = workbook.createCellStyle();
                                style.setFont(font);
                                cell.setCellStyle(style);
                            } else {
                                cell.setCellValue(text);
                            }
                        }
                    }
                }

                // Write the workbook content to the file
                try (FileOutputStream outputStream = new FileOutputStream(fileToSave)) {
                    workbook.write(outputStream);
                    statusLabel.setText("File exported successfully.");
                    System.out.println("File saved successfully to: " + fileToSave.getAbsolutePath());
                } catch (IOException ex) {
                    statusLabel.setText("Error exporting file.");
                    ex.printStackTrace();
                }
            } catch (IOException ex) {
                statusLabel.setText("Error exporting file.");
                ex.printStackTrace();
            } catch (NumberFormatException ex) {
                statusLabel.setText("Error: Unable to parse numeric value.");
                ex.printStackTrace();
            }
        }
    }







    private String extractInnerText(String htmlString) {
        int startIndex = htmlString.indexOf('>') + 1;
        int endIndex = htmlString.lastIndexOf('<');
        return htmlString.substring(startIndex, endIndex);
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new FileComparer().setVisible(true);
            }
        });
    }
}

