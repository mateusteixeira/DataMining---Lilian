package view.jpanel;

import annotations.ModuleAnnotation;
import controller.FacadeHost;
import controller.Host;
import controller.ModuleFactory;
import interfaces.Base;
import interfaces.mining.classify.BaseClassify;
import interfaces.mining.classify.ClassifierModuleInterface;
import interfaces.mining.classify.ConfusionMatrix;
import interfaces.mining.classify.EvaluationMetricModuleInterface;
import interfaces.mining.classify.PartitionModuleInterface;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.lang.reflect.InvocationTargetException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import model.ModuleAvailable;
import view.jdialog.JDialogClassify;
import view.jdialog.JDialogConfig;


public class JPanelClassify extends javax.swing.JPanel implements Observer {

    private ArrayList<JRadioButton> arrayListRadioButtonPartionModules = new ArrayList<JRadioButton>();
    
    private ArrayList<JCheckBox> arrayListCheckBoxEvalutionModules = new ArrayList<JCheckBox>();
    
    private ArrayList<PartitionModuleInterface> arrayListPartitionModuleConfigureds = new ArrayList<PartitionModuleInterface>();
    
    private ArrayList<EvaluationMetricModuleInterface> arrayListEvaluationMetricModuleConfigureds = new ArrayList<EvaluationMetricModuleInterface>();
    
    private ClassifierModuleInterface classifierModule = null;
    
    private ClassifierModuleInterface classifierModuleTrained = null;
    
    private ArrayList<Base> arrayListBases = new ArrayList<Base>();

    public JPanelClassify() {
        initComponents();
        FacadeHost.getHost().addObserver(this);
        jComboBoxAvailableTechinics.addItem("");
        for (ModuleAvailable<ClassifierModuleInterface> c : ModuleFactory.getArrayListClassifierModules()) {
            jComboBoxAvailableTechinics.addItem(c);
        }

        int c = 0;
        for (int i = 0; i < ModuleFactory.getArrayListPartionModules().size(); i++) {
            ModuleAvailable<PartitionModuleInterface> p = ModuleFactory.getArrayListPartionModules().get(i);
            try {
                final JRadioButton jRadioButton = new javax.swing.JRadioButton();
                jRadioButton.setText(p.getName());
                final PartitionModuleInterface partitionModule = p.getConstructor().newInstance();
                partitionModule.setHost(FacadeHost.getHost());

                GridBagConstraints gridBagConstraints = new java.awt.GridBagConstraints();
                gridBagConstraints.gridx = 0;
                gridBagConstraints.gridy = c;
                gridBagConstraints.ipadx = 7;
                gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
                gridBagConstraints.weightx = 55.0;
                if (i == ModuleFactory.getArrayListPartionModules().size() - 1) {
                    gridBagConstraints.weighty = 0.01;
                }
                jPanelPartitionIntern.add(jRadioButton, gridBagConstraints);

                try {
                    if (partitionModule.getPainelConfig() != null) {
                        final JButton jButton = new javax.swing.JButton();
                        jButton.setFont(new java.awt.Font("Calibri", 0, 12));
                        jButton.setText("Config");

                        jButton.addActionListener(new ActionListener() {

                            public void actionPerformed(ActionEvent ae) {
                                jRadioButton.setSelected(true);
                                JDialogConfig jDialogConfig = new JDialogConfig(FacadeHost.getViewMain(), true);
                                jDialogConfig.addPanel(partitionModule.getPainelConfig());
                                partitionModule.setJDialogConfig(jDialogConfig);
                                jDialogConfig.setVisible(true);

                            }
                        });

                        gridBagConstraints = new java.awt.GridBagConstraints();
                        gridBagConstraints.gridx = 1;
                        gridBagConstraints.gridy = c;
                        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHEAST;
                        gridBagConstraints.weightx = 45.0;
                        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 8);
                        jPanelPartitionIntern.add(jButton, gridBagConstraints);
                    }
                } catch (UnsupportedOperationException e) {
                }

                arrayListPartitionModuleConfigureds.add(partitionModule);
                buttonGroup.add(jRadioButton);
                arrayListRadioButtonPartionModules.add(jRadioButton);
                c++;

            } catch (InstantiationException ex) {
                Logger.getLogger(JPanelClassify.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
                Logger.getLogger(JPanelClassify.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalArgumentException ex) {
                Logger.getLogger(JPanelClassify.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InvocationTargetException ex) {
                Logger.getLogger(JPanelClassify.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (!arrayListRadioButtonPartionModules.isEmpty()) {
            arrayListRadioButtonPartionModules.get(0).setSelected(true);
        }

        c = 0;
        for (int i = 0; i < ModuleFactory.getArrayListEvaluationMetrics().size(); i++) {
            ModuleAvailable<EvaluationMetricModuleInterface> p = ModuleFactory.getArrayListEvaluationMetrics().get(i);
            try {
                JCheckBox jCheckBox = new javax.swing.JCheckBox();
                jCheckBox.setText(p.getName());
                final EvaluationMetricModuleInterface evaluationModule = p.getConstructor().newInstance();
                evaluationModule.setHost(FacadeHost.getHost());

                GridBagConstraints gridBagConstraints = new java.awt.GridBagConstraints();
                gridBagConstraints.gridx = 0;
                gridBagConstraints.gridy = c;
                gridBagConstraints.ipadx = 7;
                gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
                gridBagConstraints.weightx = 55.0;
                if (i == ModuleFactory.getArrayListEvaluationMetrics().size() - 1) {
                    gridBagConstraints.weighty = 0.01;
                }
                jPanelMeasuresIntern.add(jCheckBox, gridBagConstraints);

                try {
                    if (evaluationModule.getPainelConfig() != null) {
                        final JButton jButton = new javax.swing.JButton();
                        jButton.setFont(new java.awt.Font("Calibri", 0, 12));
                        jButton.setText("Config");

                        jButton.addActionListener(new ActionListener() {

                            public void actionPerformed(ActionEvent ae) {
                                JDialogConfig jDialogConfig = new JDialogConfig(FacadeHost.getViewMain(), true);
                                jDialogConfig.addPanel(evaluationModule.getPainelConfig());
                                evaluationModule.setJDialogConfig(jDialogConfig);
                                jDialogConfig.setVisible(true);
                            }
                        });

                        gridBagConstraints = new java.awt.GridBagConstraints();
                        gridBagConstraints.gridx = 1;
                        gridBagConstraints.gridy = c;
                        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHEAST;
                        gridBagConstraints.weightx = 45.0;
                        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 8);
                        jPanelPartitionIntern.add(jButton, gridBagConstraints);
                    }
                } catch (UnsupportedOperationException e) {
                }


                arrayListEvaluationMetricModuleConfigureds.add(evaluationModule);
                arrayListCheckBoxEvalutionModules.add(jCheckBox);
                c++;

            } catch (InstantiationException ex) {
                Logger.getLogger(JPanelClassify.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
                Logger.getLogger(JPanelClassify.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalArgumentException ex) {
                Logger.getLogger(JPanelClassify.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InvocationTargetException ex) {
                Logger.getLogger(JPanelClassify.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        for (JCheckBox jCheckBoxEvaluationModule : arrayListCheckBoxEvalutionModules) {
            jCheckBoxEvaluationModule.setSelected(true);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        buttonGroup = new javax.swing.ButtonGroup();
        jPanel = new javax.swing.JPanel();
        jLabelAvailableTechinics = new javax.swing.JLabel();
        jComboBoxAvailableTechinics = new javax.swing.JComboBox();
        jButtonConfiguration = new javax.swing.JButton();
        jPanelPartition = new javax.swing.JPanel();
        jScrollPanePartition = new javax.swing.JScrollPane();
        jPanelPartitionIntern = new javax.swing.JPanel();
        jPanelMeasures = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jPanelMeasuresIntern = new javax.swing.JPanel();
        jScrollPaneRunning = new javax.swing.JScrollPane();
        jButtonVisualize = new javax.swing.JButton();
        jButtonTrain = new javax.swing.JButton();
        jButtonClassify = new javax.swing.JButton();
        jScrollPaneResult = new javax.swing.JScrollPane();
        jListResult = new javax.swing.JList();

        jPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Técnica de Classificação", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Calibri", 1, 18))); // NOI18N
        jPanel.setLayout(new java.awt.GridBagLayout());

        jLabelAvailableTechinics.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        jLabelAvailableTechinics.setText("Técnica a ser utilizada:"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 27, 0, 0);
        jPanel.add(jLabelAvailableTechinics, gridBagConstraints);

        jComboBoxAvailableTechinics.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxAvailableTechinicsActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.ipadx = 300;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(1, 36, 0, 0);
        jPanel.add(jComboBoxAvailableTechinics, gridBagConstraints);

        jButtonConfiguration.setFont(new java.awt.Font("Calibri", 0, 13)); // NOI18N
        jButtonConfiguration.setText("Configurações");
        jButtonConfiguration.setEnabled(false);
        jButtonConfiguration.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonConfigurationActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 30.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 6, 0, 0);
        jPanel.add(jButtonConfiguration, gridBagConstraints);

        jPanelPartition.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Particionamento", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Calibri", 1, 12))); // NOI18N

        jScrollPanePartition.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jScrollPanePartition.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPanePartition.setViewportBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        jPanelPartitionIntern.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jPanelPartitionIntern.setLayout(new java.awt.GridBagLayout());
        jScrollPanePartition.setViewportView(jPanelPartitionIntern);

        javax.swing.GroupLayout jPanelPartitionLayout = new javax.swing.GroupLayout(jPanelPartition);
        jPanelPartition.setLayout(jPanelPartitionLayout);
        jPanelPartitionLayout.setHorizontalGroup(
            jPanelPartitionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPanePartition, javax.swing.GroupLayout.DEFAULT_SIZE, 205, Short.MAX_VALUE)
        );
        jPanelPartitionLayout.setVerticalGroup(
            jPanelPartitionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPanePartition, javax.swing.GroupLayout.DEFAULT_SIZE, 124, Short.MAX_VALUE)
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.ipadx = 180;
        gridBagConstraints.ipady = 80;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weighty = 35.0;
        gridBagConstraints.insets = new java.awt.Insets(6, 0, 0, 0);
        jPanel.add(jPanelPartition, gridBagConstraints);

        jPanelMeasures.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Avaliação", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Calibri", 1, 12))); // NOI18N

        jScrollPane3.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jScrollPane3.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane3.setViewportBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        jPanelMeasuresIntern.setLayout(new java.awt.GridBagLayout());
        jScrollPane3.setViewportView(jPanelMeasuresIntern);

        javax.swing.GroupLayout jPanelMeasuresLayout = new javax.swing.GroupLayout(jPanelMeasures);
        jPanelMeasures.setLayout(jPanelMeasuresLayout);
        jPanelMeasuresLayout.setHorizontalGroup(
            jPanelMeasuresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 205, Short.MAX_VALUE)
            .addGroup(jPanelMeasuresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 205, Short.MAX_VALUE))
        );
        jPanelMeasuresLayout.setVerticalGroup(
            jPanelMeasuresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 124, Short.MAX_VALUE)
            .addGroup(jPanelMeasuresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 124, Short.MAX_VALUE))
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.ipadx = 180;
        gridBagConstraints.ipady = 80;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weighty = 35.0;
        gridBagConstraints.insets = new java.awt.Insets(6, 0, 0, 0);
        jPanel.add(jPanelMeasures, gridBagConstraints);

        jScrollPaneRunning.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPaneRunning.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.gridheight = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 363;
        gridBagConstraints.ipady = 320;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(14, 6, 0, 6);
        jPanel.add(jScrollPaneRunning, gridBagConstraints);

        jButtonVisualize.setFont(new java.awt.Font("Calibri", 0, 13)); // NOI18N
        jButtonVisualize.setText("Visualizar Modelo");
        jButtonVisualize.setEnabled(false);
        jButtonVisualize.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonVisualizeActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.SOUTHEAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 6, 6);
        jPanel.add(jButtonVisualize, gridBagConstraints);

        jButtonTrain.setFont(new java.awt.Font("Calibri", 0, 13)); // NOI18N
        jButtonTrain.setText("Treinar");
        jButtonTrain.setEnabled(false);
        jButtonTrain.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonTrainActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(6, 6, 6, 0);
        jPanel.add(jButtonTrain, gridBagConstraints);

        jButtonClassify.setFont(new java.awt.Font("Calibri", 0, 13)); // NOI18N
        jButtonClassify.setText("Classificar");
        jButtonClassify.setEnabled(false);
        jButtonClassify.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonClassifyActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(6, 6, 0, 0);
        jPanel.add(jButtonClassify, gridBagConstraints);

        jScrollPaneResult.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Lista de Resultados", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Calibri", 1, 12))); // NOI18N

        jListResult.setModel(new DefaultListModel());
        jListResult.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jListResult.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jListResultMouseClicked(evt);
            }
        });
        jListResult.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jListResultKeyReleased(evt);
            }
        });
        jScrollPaneResult.setViewportView(jListResult);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weighty = 30.0;
        jPanel.add(jScrollPaneResult, gridBagConstraints);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 666, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 666, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 501, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 501, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonVisualizeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonVisualizeActionPerformed
        jButtonVisualizeActionPerformed();
    }//GEN-LAST:event_jButtonVisualizeActionPerformed

    private void jListResultMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jListResultMouseClicked
        jListResultActionPerformed();
    }//GEN-LAST:event_jListResultMouseClicked

    private void jComboBoxAvailableTechinicsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxAvailableTechinicsActionPerformed
        jComboBoxAvailableTechinicsActionPerformed();
    }//GEN-LAST:event_jComboBoxAvailableTechinicsActionPerformed

    private void jButtonConfigurationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonConfigurationActionPerformed
        jButtonConfigurationActionPerformed();
    }//GEN-LAST:event_jButtonConfigurationActionPerformed

    private void jButtonTrainActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonTrainActionPerformed
        jButtonTrainActionPerformed();
    }//GEN-LAST:event_jButtonTrainActionPerformed

    private void jListResultKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jListResultKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_UP
                || evt.getKeyCode() == KeyEvent.VK_DOWN
                || evt.getKeyCode() == KeyEvent.VK_PAGE_DOWN
                || evt.getKeyCode() == KeyEvent.VK_PAGE_UP
                || evt.getKeyCode() == KeyEvent.VK_END
                || evt.getKeyCode() == KeyEvent.VK_HOME) {
            jListResultActionPerformed();
        }
    }//GEN-LAST:event_jListResultKeyReleased

    private void jButtonClassifyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonClassifyActionPerformed
       jButtonClassifyActionPerformed();
    }//GEN-LAST:event_jButtonClassifyActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup;
    private javax.swing.JButton jButtonClassify;
    private javax.swing.JButton jButtonConfiguration;
    private javax.swing.JButton jButtonTrain;
    private javax.swing.JButton jButtonVisualize;
    private javax.swing.JComboBox jComboBoxAvailableTechinics;
    private javax.swing.JLabel jLabelAvailableTechinics;
    private javax.swing.JList jListResult;
    private javax.swing.JPanel jPanel;
    private javax.swing.JPanel jPanelMeasures;
    private javax.swing.JPanel jPanelMeasuresIntern;
    private javax.swing.JPanel jPanelPartition;
    private javax.swing.JPanel jPanelPartitionIntern;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPanePartition;
    private javax.swing.JScrollPane jScrollPaneResult;
    private javax.swing.JScrollPane jScrollPaneRunning;
    // End of variables declaration//GEN-END:variables

    public void update(Observable o, Object o1) {
        if (o instanceof Host) {
            Host h = (Host) o;
            if ((o1 instanceof Base) && ((Base) o1).hasMeta()) {
                arrayListBases.add((Base) o1);
            }
            
            if(o1 instanceof ClassifierModuleInterface){
                ClassifierModuleInterface classifierModule = (ClassifierModuleInterface)o1;
                classifierModule.setHost(h);

                ClassifyText classifyJTextArea = new ClassifyText();
                classifyJTextArea.setModuleAvailable(classifierModule);
                classifyJTextArea.setJTextArea(classifierModule.getJTextArea());
                ((DefaultListModel) jListResult.getModel()).addElement(classifyJTextArea);
            }
        }
    }

    private void jComboBoxAvailableTechinicsActionPerformed() {
        if (jComboBoxAvailableTechinics.getSelectedItem() instanceof ModuleAvailable) {
            try {
                classifierModule = ((ModuleAvailable<ClassifierModuleInterface>) jComboBoxAvailableTechinics.getSelectedItem()).getConstructor().newInstance();
                classifierModule.setHost(FacadeHost.getHost());
                //classifierModule.setTextArea(jTextAreaRunning);
                if (classifierModule.getPainelConfig() != null) {
                    jButtonConfiguration.setEnabled(true);
                } else {
                    jButtonConfiguration.setEnabled(false);
                }
                jButtonTrain.setEnabled(true);
            } catch (InstantiationException ex) {
                Logger.getLogger(JPanelPreprocess.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
                Logger.getLogger(JPanelPreprocess.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalArgumentException ex) {
                Logger.getLogger(JPanelPreprocess.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InvocationTargetException ex) {
                Logger.getLogger(JPanelPreprocess.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            jButtonConfiguration.setEnabled(false);
            jButtonTrain.setEnabled(false);
            classifierModule = null;
        }
    }

    private void jButtonConfigurationActionPerformed() {
        JDialogConfig jDialogConfig = new JDialogConfig(FacadeHost.getViewMain(), true);
        jDialogConfig.addPanel(classifierModule.getPainelConfig());
        jDialogConfig.setTitle("Configurações do Classificador");
        jDialogConfig.setSize(jDialogConfig.getWidth()<300?300:jDialogConfig.getWidth(), jDialogConfig.getHeight());
        classifierModule.setJDialogConfig(jDialogConfig);
        jDialogConfig.setVisible(true);
    }

    private void jButtonVisualizeActionPerformed() {
        JDialogConfig jDialogConfig = new JDialogConfig(FacadeHost.getViewMain(), true);
        jDialogConfig.addPanel(classifierModuleTrained.getCreatedModel());
        jDialogConfig.setVisible(true);
    }

    private synchronized void jListResultActionPerformed() {
        ClassifyText classifyJTextArea = (ClassifyText) jListResult.getSelectedValue();
        classifierModuleTrained = classifyJTextArea.getModuleAvailable();
        jScrollPaneRunning.setViewportView(classifyJTextArea.getJTextArea());

        jButtonClassify.setEnabled(true);
        revalidate();
    }

    private void jButtonClassifyActionPerformed() {
        Base base = arrayListBases.get(arrayListBases.size()-1);
        JDialogClassify jd = new JDialogClassify(FacadeHost.getViewMain(), true, base, classifierModuleTrained);
        jd.setVisible(true);
    }

    public Object getSave() {
        return classifierModuleTrained;
    }

    public void setOpen(Object o) {
        this.classifierModuleTrained = (ClassifierModuleInterface) o;
    }

    class ClassifyText {

        final DateFormat formatter = new SimpleDateFormat("HH:mm:ss");
        private JTextArea text;
        private ClassifierModuleInterface moduleAvailable;
        private Date date;

        public ClassifyText() {
            date = new Date();
        }

        public JTextArea getJTextArea() {
            return text;
        }

        public void setJTextArea(JTextArea text) {
            this.text = text;
        }

        public ClassifierModuleInterface getModuleAvailable() {
            return moduleAvailable;
        }

        public void setModuleAvailable(ClassifierModuleInterface moduleAvailable) {
            this.moduleAvailable = moduleAvailable;
        }

        @Override
        public String toString() {
            return formatter.format(date)
                    + " " + moduleAvailable.getClass().getAnnotation(ModuleAnnotation.class).name();
        }
    }

    private void jButtonTrainActionPerformed() {
        new Thread(new Runnable() {

            public void run() {
                jButtonTrain.setEnabled(false);
                jButtonClassify.setEnabled(false);
                jButtonVisualize.setEnabled(false);

                ClassifierModuleInterface classifierModule = JPanelClassify.this.classifierModule.clone();

                final JTextArea jTextAreaRunning = new javax.swing.JTextArea();
                jTextAreaRunning.setColumns(20);
                jTextAreaRunning.setEditable(false);
                jTextAreaRunning.setRows(5);
                jTextAreaRunning.addComponentListener(new java.awt.event.ComponentAdapter() {
                    public void componentResized(java.awt.event.ComponentEvent evt) {
                        jTextAreaRunning.setCaretPosition(jTextAreaRunning.getText().length());
                    }
                });
                jScrollPaneRunning.setViewportView(jTextAreaRunning);
                classifierModule.setTextArea(jTextAreaRunning);

                ClassifyText classifyJTextArea = new ClassifyText();
                classifyJTextArea.setModuleAvailable(classifierModule);
                classifyJTextArea.setJTextArea(jTextAreaRunning);

                ((DefaultListModel) jListResult.getModel()).addElement(classifyJTextArea);
                jListResult.setSelectedIndex(jListResult.getModel().getSize()-1);

                BaseClassify[] baseClassifiers = null;
                Base base = arrayListBases.get(arrayListBases.size() - 1);
                for (int i = 0; i < arrayListRadioButtonPartionModules.size(); i++) {
                    if (arrayListRadioButtonPartionModules.get(i).isSelected()) {
                        baseClassifiers = arrayListPartitionModuleConfigureds.get(i).partition(base);
                        break;
                    }
                }
                ArrayList<ConfusionMatrix> arrayListConfusionMatrixs = new ArrayList<ConfusionMatrix>();

                long tempo = System.currentTimeMillis();

                jTextAreaRunning.append("============== ");
                classifierModule.createInstanceClissify(base.getAtributes(), base.getClasses());
                jTextAreaRunning.append(" ==============\n");
                for (BaseClassify baseClassify : baseClassifiers) {
                    
                    classifierModule.train(baseClassify.getTrain().getInput(), baseClassify.getTrain().getOutput());
                    ConfusionMatrix confusionMatrix = new ConfusionMatrix();
                    confusionMatrix.setClasses(base.getClasses());
                    for (int i = 0; i < baseClassify.getTest().getInput().length; i++) {
                        confusionMatrix.addValue(baseClassify.getTest().getOutput()[i],
                                                    classifierModule.test(baseClassify.getTest().getInput()[i]));
                    }
                    jTextAreaRunning.append("\n============== Matriz de Confusão ==============\n");
                    jTextAreaRunning.append(confusionMatrix.toString() + "\n\n");
                    arrayListConfusionMatrixs.add(confusionMatrix);
                }
                
                   if(baseClassifiers.length>1){
                    ConfusionMatrix confusionMatrixF = new ConfusionMatrix();
                    confusionMatrixF.setClasses(base.getClasses());
                    for (ConfusionMatrix confusionMatrix : arrayListConfusionMatrixs) {
                        for (Object classI : confusionMatrix.getClasses()) {
                            for (Object classJ : confusionMatrix.getClasses()) {
                                confusionMatrixF.setValue(classI, classJ, confusionMatrixF.getValue(classI, classJ) + confusionMatrix.getValue(classI, classJ));
                            }
                        }
                    }
                    jTextAreaRunning.append("\n============== Matriz de Confusão Consolidada ==============\n");
                    jTextAreaRunning.append(confusionMatrixF.toString() + "\n\n");
                }
                
                tempo = System.currentTimeMillis() -tempo;
                jTextAreaRunning.append(util.Util.getHoraMinSegMiliSeg(tempo) + "\n\n");

                jTextAreaRunning.append("============== Métricas de Avaliação ==============\n\n");
                for (int i = 0; i < arrayListCheckBoxEvalutionModules.size(); i++) {
                    if (arrayListCheckBoxEvalutionModules.get(i).isSelected()) {
                        jTextAreaRunning.append("==============\n");
                        arrayListEvaluationMetricModuleConfigureds.get(i).setTextArea(jTextAreaRunning);
                        arrayListEvaluationMetricModuleConfigureds.get(i).evaluation(arrayListConfusionMatrixs.toArray(new ConfusionMatrix[0]));
                        jTextAreaRunning.append("==============\n\n");
                    }
                }
                jButtonTrain.setEnabled(true);
                jListResultActionPerformed();
            }
        }).start();
    }
}
