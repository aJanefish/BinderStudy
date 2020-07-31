package com.example.ipcdemo.manager;

public class DemoManager {

    private static DemoManager demoManager = new DemoManager();
    private static int values = 1;

    private DemoManager() {

    }

    public static DemoManager getDemoManager() {
        return demoManager;
    }

    public static int getValues() {
        return values;
    }

    public static void setValues(int values) {
        DemoManager.values = values;
    }
}
