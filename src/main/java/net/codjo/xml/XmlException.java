/*
 * Team : AGF AM / OSI / SI / BO
 *
 * Copyright (c) 2001 AGF Asset Management.
 */
package net.codjo.xml;
import java.io.PrintStream;
import java.io.PrintWriter;
/**
 * Lancé par la facade xml pour indiquer une erreur dans la couche d'implémentation.
 *
 * @author MARCONA
 */
public class XmlException extends Exception {
    private Exception causedBy;

    public XmlException(Exception cause) {
        super("Exception dans le Fast xml parser : " + cause.getLocalizedMessage());
        this.causedBy = cause;
    }

    public void printStackTrace(PrintWriter writer) {
        super.printStackTrace(writer);
        if (getCausedBy() != null) {
            writer.println(" ---- cause ---- ");
            getCausedBy().printStackTrace(writer);
        }
    }


    public Exception getCausedBy() {
        return causedBy;
    }


    public void printStackTrace() {
        super.printStackTrace();
        if (getCausedBy() != null) {
            System.err.println(" ---- cause ---- ");
            getCausedBy().printStackTrace();
        }
    }


    public void printStackTrace(PrintStream stream) {
        super.printStackTrace(stream);
        if (getCausedBy() != null) {
            stream.println(" ---- cause ---- ");
            getCausedBy().printStackTrace(stream);
        }
    }
}
