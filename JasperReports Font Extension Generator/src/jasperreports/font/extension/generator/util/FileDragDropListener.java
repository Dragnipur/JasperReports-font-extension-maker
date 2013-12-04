/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jasperreports.font.extension.generator.util;

import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetEvent;
import java.awt.dnd.DropTargetListener;

/**
 *
 * @author Pieter
 */
public class FileDragDropListener implements DropTargetListener{

    @Override
    public void dragEnter(DropTargetDragEvent dtde) {
        System.out.println("dragEnter");
    }

    @Override
    public void dragOver(DropTargetDragEvent dtde) {
        System.out.println("dragOver");
    }

    @Override
    public void dropActionChanged(DropTargetDragEvent dtde) {
        System.out.println("dropChanged");
    }

    @Override
    public void dragExit(DropTargetEvent dte) {
        System.out.println("dragExit");
    }

    @Override
    public void drop(DropTargetDropEvent dtde) {
        System.out.println("dropped");
    }
    
}
