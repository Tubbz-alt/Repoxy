import utils.ControllerConfig;
import utils.LocalhostIpSupplier;

import java.io.IOException;
import java.util.ArrayList;

import static utils.CommonMain.*;

public class Main {
    private static String LOCALHOST;
    private static String CONT_4 = "192.168.1.244";
    private static String CONT_5 = "192.168.1.245";
    public static int OF_PORT = 6833;
    public static int CONTROLLER_PORT = 6834;
    public static int REPLICATED_CONTROLLER_PORT = 6835;

    public static void main(String[] args) throws IOException {
        setupLogging();
        boolean oneMachineRun = false;

        //noinspection ConstantConditions
        if (oneMachineRun) {
            LOCALHOST = "127.0.0.1";
            CONT_4 = "127.0.0.1";
            CONT_5 = "127.0.0.1";
        } else {
            // Exclude lo* interfaces (loopback) exclude loopback interfaces when not running on single machine
            LOCALHOST = LocalhostIpSupplier.getLocalHostLANAddress();
            CONT_4 = "192.168.1.244";
            CONT_5 = "192.168.1.245";
        }

        System.out.println(String.format("Local IP: [%s] Ports: [%d] [%d] [%d]",
                LOCALHOST, OF_PORT, CONTROLLER_PORT, REPLICATED_CONTROLLER_PORT));


        ArrayList<ControllerConfig> configs = new ArrayList<>();
        configs.add(new ControllerConfig(CONT_4, CONTROLLER_PORT));
        configs.add(new ControllerConfig(CONT_5, REPLICATED_CONTROLLER_PORT));

        startProxy(LOCALHOST, OF_PORT, configs);
        stopProxy();
    }
}
