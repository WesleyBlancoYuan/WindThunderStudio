package com.windthunderstudio.ui;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Properties;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.border.EtchedBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.windthunderstudio.logic.util.CTS;
import com.windthunderstudio.logic.util.I18N_Manager;
import com.windthunderstudio.ui.elements.BoldLabel;
import com.windthunderstudio.ui.elements.IconButton;
import com.windthunderstudio.ui.elements.PlainButton;

import net.miginfocom.swing.MigLayout;

public class TaskDialog extends JDialog {
    private static final Logger log = LogManager.getLogger(TaskDialog.class);
    private Properties localeProp = I18N_Manager.loadLocale();
    
    private BoldLabel titleDesc;
    private IconButton create;
    private IconButton edit;
    private IconButton delete;
    
    private JTable tasks;
    public TaskDialog() {
        createTaskDialog();
    }
    
    private void createTaskDialog() {
        setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
        
        setTitle(localeProp.getProperty(CTS.DIALOG_TITLE_MANAGE_ALARM));
        setLayout(new MigLayout("insets 10, fill", "[]", "[]5[]5[fill, grow]"));
        setBounds(0, 0, 600, 500);
        
        JPanel topPanel = new JPanel();
        topPanel.setOpaque(false);
        topPanel.setLayout(new MigLayout("insets 5, fill", "[]push[]10[]10[]", "[]"));
        add(topPanel, "cell 0 0, grow");
        
        titleDesc = new BoldLabel();
        titleDesc.setText(localeProp.getProperty(CTS.DIALOG_TEXT_MANAGE_ALARM));
        titleDesc.setTextKey(CTS.DIALOG_TEXT_MANAGE_ALARM);
        topPanel.add(titleDesc, "cell 0 0, grow");
        
        create = new IconButton();
        ImageIcon createIcon = new ImageIcon(CTS.ICON_CREATE_PATH);
//        create.setIcon(new ImageIcon(createIcon.getImage().getScaledInstance(15, 15, Image.SCALE_SMOOTH)));
        create.setIcon(createIcon);
        create.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
            }
        });
        topPanel.add(create, "cell 1 0, shrink, w 30!, h 30!");
        
        edit = new IconButton();
        edit.setEnabled(false);
        ImageIcon editIcon = new ImageIcon(CTS.ICON_EDIT_PATH);
        edit.setIcon(new ImageIcon(editIcon.getImage().getScaledInstance(15, 15, Image.SCALE_SMOOTH)));
        edit.setIcon(editIcon);
        topPanel.add(edit, "cell 2 0, shrink, w 30!, h 30!");
        
        delete = new IconButton();
        delete.setEnabled(false);
        ImageIcon deleteIcon = new ImageIcon(CTS.ICON_DELETE_PATH);
//        delete.setIcon(new ImageIcon(deleteIcon.getImage().getScaledInstance(15, 15, Image.SCALE_SMOOTH)));
        delete.setIcon(deleteIcon);
        
        topPanel.add(delete, "cell 3 0, shrink, w 30!, h 30!");
        
        add(new JSeparator(), "cell 0 1, grow");
        
        tasks = new JTable();
        String[] colNames = new String[] {
                localeProp.getProperty(CTS.TABLE_COLNAME_TASK_NAME), 
                localeProp.getProperty(CTS.TABLE_COLNAME_TASK_DESC)};
        TableModel model = new DefaultTableModel(colNames, 0);
        
        tasks.setModel(model);
        tasks.setAutoCreateRowSorter(false);
        tasks.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
        tasks.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
        tasks.setColumnSelectionAllowed(false);
        tasks.setFillsViewportHeight(false);
        tasks.setGridColor(Color.LIGHT_GRAY);
        tasks.setOpaque(true);
        tasks.setRowHeight(20);
        tasks.setRowSelectionAllowed(true);
        tasks.setShowGrid(false);
        tasks.setUpdateSelectionOnSort(true);
        
        tasks.setPreferredScrollableViewportSize(tasks.getPreferredSize());
        
        tasks.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            // disable edit and disable button when there is selection
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (e.getFirstIndex() >= 0 && e.getLastIndex() >= 0) {
                    delete.setEnabled(true);
                    edit.setEnabled(true);
                } else {
                    delete.setEnabled(false);
                    edit.setEnabled(false);
                }
            }
        });
        JScrollPane tasksSP = new JScrollPane(tasks);
        tasksSP.setViewportBorder(null);
        tasksSP.getViewport().setOpaque(true);
        tasksSP.setBackground(Color.LIGHT_GRAY);
        
        add(tasksSP, "cell 0 2, grow");
        
        pack();
        setBounds(0, 0, 600, 500);
        setLocationRelativeTo(null);
        setVisible(false);
    }

    public JTable getTasks() {
        return tasks;
    }

    public void setTasks(JTable tasks) {
        this.tasks = tasks;
    }

    public BoldLabel getTitleDesc() {
        return titleDesc;
    }

    
}
