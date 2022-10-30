/*
* Copyright (c) 1995, 2008, Oracle and/or its affiliates. All rights reserved.
**/ 

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.print.*;

public class PrintUIWindow implements Printable, ActionListener {

    JFrame frameToPrint;

    public int print(Graphics g, PageFormat pf, int page) throws PrinterException {

        if (page > 0) { /* We have only one page, and 'page' is zero-based */
            return NO_SUCH_PAGE;
        }

        Graphics2D g2d = (Graphics2D)g;
        g2d.translate(pf.getImageableX(), pf.getImageableY());

        /* Now print the window and its visible contents */
        frameToPrint.printAll(g);

        /* tell the caller that this page is part of the printed document */
        return PAGE_EXISTS;
    }

    public void actionPerformed(ActionEvent e) {
         PrinterJob job = PrinterJob.getPrinterJob();
         job.setPrintable(this);
         boolean ok = job.printDialog();
         if (ok) {
             try {
                job.print();
             } catch (PrinterException ex) {
                System.out.println("no computer found :(");
             }
         }
    }

    public PrintUIWindow(JFrame f) {
        frameToPrint = f;
        PrinterJob job = PrinterJob.getPrinterJob();
         job.setPrintable(this);
         boolean ok = job.printDialog();
         if (ok) {
             try {
                job.print();
             } catch (PrinterException ex) {
                System.out.println("no computer found :(");
             }
         }
    }
}