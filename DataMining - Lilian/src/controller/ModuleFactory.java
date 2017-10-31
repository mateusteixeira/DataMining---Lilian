package controller;

import annotations.ClassifierModuleAnnotation;
import annotations.ElicitedBasesModuleAnnotation;
import annotations.EvaluationMetricModuleAnnotation;
import annotations.ModuleAnnotation;
import interfaces.mining.classify.ClassifierModuleInterface;
import interfaces.mining.classify.EvaluationMetricModuleInterface;
import interfaces.mining.classify.PartitionModuleInterface;
import interfaces.mining.clustering.ClusteringModuleInterface;
import interfaces.preprocess.ElicitedBasesModuleInterface;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;
import model.ModuleAvailable;
import moduledefault.classify.knn.KnnClassifierModule;
import moduledefault.elicitedbases.arff.ArffElicitedBases;
import moduledefault.evaluationmetric.accuracy.AccuracyEvaluationMetricModule;
import moduledefault.evaluationmetric.precision.PrecisionEvaluationMetricModule;
import moduledefault.evaluationmetric.recall.RecallEvaluationMetricModule;
import moduledefault.partition.holdout.HoldoutPartionModule;

public class ModuleFactory {

    private static ArrayList<ModuleAvailable<ClassifierModuleInterface>> arrayListClassifierModules
            = new ArrayList<ModuleAvailable<ClassifierModuleInterface>>();
    
    private static ArrayList<ModuleAvailable<ElicitedBasesModuleInterface>> arrayListElicitedBasesModules
            = new ArrayList<ModuleAvailable<ElicitedBasesModuleInterface>>();
    
    private static ArrayList<ModuleAvailable<PartitionModuleInterface>> arrayListPartionModules
            = new ArrayList<ModuleAvailable<PartitionModuleInterface>>();
    
    private static ArrayList<ModuleAvailable<EvaluationMetricModuleInterface>> arrayListEvaluationMetrics
            = new ArrayList<ModuleAvailable<EvaluationMetricModuleInterface>>();

        private static ArrayList<ModuleAvailable<ClusteringModuleInterface>> arrayListClusteringModules
            = new ArrayList<ModuleAvailable<ClusteringModuleInterface>>();
    
    private static URLClassLoader loader;

    static {
        try {
            File f = new File("Modules/");
            ArrayList<URL> urls = new ArrayList<URL>();
            if (f.listFiles() != null) {
                for (File file : f.listFiles()) {
                    if (file.getAbsolutePath().endsWith(".jar")) {
                        urls.add(file.toURI().toURL());
                    }
                }
            }

            ModuleAvailable<ElicitedBasesModuleInterface> moduleAvailableArff
                    = new ModuleAvailable<ElicitedBasesModuleInterface>();
            moduleAvailableArff.setName(ArffElicitedBases.class.getAnnotation(ModuleAnnotation.class).name());
            moduleAvailableArff.setConstructor((Constructor) ArffElicitedBases.class.getConstructor());
            arrayListElicitedBasesModules.add(moduleAvailableArff);

            ModuleAvailable<PartitionModuleInterface> moduleAvailableHoldout
                    = new ModuleAvailable<PartitionModuleInterface>();
            moduleAvailableHoldout.setName(HoldoutPartionModule.class.getAnnotation(ModuleAnnotation.class).name());
            moduleAvailableHoldout.setConstructor((Constructor) HoldoutPartionModule.class.getConstructor());
            arrayListPartionModules.add(moduleAvailableHoldout);

            ModuleAvailable<EvaluationMetricModuleInterface> moduleAvailableAccuracy
                    = new ModuleAvailable<EvaluationMetricModuleInterface>();
            moduleAvailableAccuracy.setName(AccuracyEvaluationMetricModule.class.getAnnotation(ModuleAnnotation.class).name());
            moduleAvailableAccuracy.setConstructor((Constructor) AccuracyEvaluationMetricModule.class.getConstructor());
            arrayListEvaluationMetrics.add(moduleAvailableAccuracy);

            ModuleAvailable<EvaluationMetricModuleInterface> moduleAvailablePrecision
                    = new ModuleAvailable<EvaluationMetricModuleInterface>();
            moduleAvailablePrecision.setName(PrecisionEvaluationMetricModule.class.getAnnotation(ModuleAnnotation.class).name());
            moduleAvailablePrecision.setConstructor((Constructor) PrecisionEvaluationMetricModule.class.getConstructor());
            arrayListEvaluationMetrics.add(moduleAvailablePrecision);

            ModuleAvailable<EvaluationMetricModuleInterface> moduleAvailableRecall
                    = new ModuleAvailable<EvaluationMetricModuleInterface>();
            moduleAvailableRecall.setName(RecallEvaluationMetricModule.class.getAnnotation(ModuleAnnotation.class).name());
            moduleAvailableRecall.setConstructor((Constructor) RecallEvaluationMetricModule.class.getConstructor());
            arrayListEvaluationMetrics.add(moduleAvailableRecall);

            ModuleAvailable<ClassifierModuleInterface> moduleAvailableKnn
                    = new ModuleAvailable<ClassifierModuleInterface>();
            moduleAvailableKnn.setName(KnnClassifierModule.class.getAnnotation(ModuleAnnotation.class).name());
            moduleAvailableKnn.setConstructor((Constructor) KnnClassifierModule.class.getConstructor());
            arrayListClassifierModules.add(moduleAvailableKnn);

            loader = new URLClassLoader(urls.toArray(new URL[0]));
            if (f.listFiles() != null) {
                for (File file : f.listFiles()) {
                    if (file.getAbsolutePath().endsWith(".jar")) {
                        ZipFile zipFile = null;
                        try {
                            zipFile = new ZipFile(file);
                            for (Enumeration e = zipFile.entries(); e.hasMoreElements();) {
                                ZipEntry zipEntry = (ZipEntry) e.nextElement();
                                if (zipEntry.isDirectory()) {
                                    continue;
                                }

                                String name = zipEntry.getName();
                                if (name.endsWith(".class")) {
                                    name = name.replaceAll("/", ".").replaceAll(".class", "");
                                    Class cl = Class.forName(name, true, loader);
                                    ModuleAnnotation module = (ModuleAnnotation) cl.getAnnotation(ModuleAnnotation.class);
                                    if (module != null) {
                                        String nameModule = module.name();

                                        //Elicitar qual modulo
                                        if (cl.getAnnotation(ClassifierModuleAnnotation.class) != null) {
                                            ModuleAvailable<ClassifierModuleInterface> moduleAvailable
                                                    = new ModuleAvailable<ClassifierModuleInterface>();
                                            moduleAvailable.setName(nameModule);
                                            moduleAvailable.setConstructor((Constructor<ClassifierModuleInterface>) cl.getConstructor());

                                            arrayListClassifierModules.add(moduleAvailable);
                                        }

                                        if (cl.getAnnotation(ElicitedBasesModuleAnnotation.class) != null) {
                                            ModuleAvailable<ElicitedBasesModuleInterface> moduleAvailable
                                                    = new ModuleAvailable<ElicitedBasesModuleInterface>();
                                            moduleAvailable.setName(nameModule);
                                            moduleAvailable.setConstructor((Constructor<ElicitedBasesModuleInterface>) cl.getConstructor());

                                            arrayListElicitedBasesModules.add(moduleAvailable);
                                        }

                                        if (cl.getAnnotation(EvaluationMetricModuleAnnotation.class) != null) {
                                            ModuleAvailable<EvaluationMetricModuleInterface> moduleAvailable
                                                    = new ModuleAvailable<EvaluationMetricModuleInterface>();
                                            moduleAvailable.setName(nameModule);
                                            moduleAvailable.setConstructor((Constructor<EvaluationMetricModuleInterface>) cl.getConstructor());

                                            arrayListEvaluationMetrics.add(moduleAvailable);
                                        }
                                    }
                                }
                            }
                        } catch (ZipException ex) {
                            Logger.getLogger(ModuleFactory.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (IOException ex) {
                            Logger.getLogger(ModuleFactory.class.getName()).log(Level.SEVERE, null, ex);
                        } finally {
                            if (zipFile != null) {
                                try {
                                    zipFile.close();
                                } catch (IOException ex) {
                                    Logger.getLogger(ModuleFactory.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }
                        }
                    }
                }
            }

//            URL urls[] = new URL[f.listFiles().length];
//            for (int i = 0; i < f.listFiles().length; i++){
//                urls[i] = new URL(f.listFiles()[i].toURI().toURL().toString());
//            }
//
//            URLClassLoader loader = new URLClassLoader(urls);
//            for (int i = 0; i < f.listFiles().length; i++) {
//                Class cl = Class.forName(f.listFiles()[i].getName().substring(0, f.listFiles()[i].getName().length() - 4).toLowerCase() + ".PainelAlgoritmo", true, loader);
//                Constructor c = cl.getConstructor(Class.forName("java.lang.String"));
//                DadosSobreAlgoritmo d = (DadosSobreAlgoritmo) cl.getAnnotations()[0];
//                algoritmosDisponiveis.add(new AlgoritmoDisponivel(d.Algoritmo(), c));
//            }
        } catch (NoSuchMethodException ex) {
            Logger.getLogger(ModuleFactory.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SecurityException ex) {
            Logger.getLogger(ModuleFactory.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ModuleFactory.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(ModuleFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private ModuleFactory() {
    }

    public static ArrayList<ModuleAvailable<ClassifierModuleInterface>> getArrayListClassifierModules() {
        return arrayListClassifierModules;
    }
    
    public static ArrayList<ModuleAvailable<ClusteringModuleInterface>> getArrayListClusteringModules(){
        return arrayListClusteringModules;
    }

    public static ArrayList<ModuleAvailable<ElicitedBasesModuleInterface>> getArrayListElicitedBasesModules() {
        return arrayListElicitedBasesModules;
    }

    public static ArrayList<ModuleAvailable<PartitionModuleInterface>> getArrayListPartionModules() {
        return arrayListPartionModules;
    }

    public static ArrayList<ModuleAvailable<EvaluationMetricModuleInterface>> getArrayListEvaluationMetrics() {
        return arrayListEvaluationMetrics;
    }

    public static URLClassLoader getLoader() {
        return loader;
    }
}
