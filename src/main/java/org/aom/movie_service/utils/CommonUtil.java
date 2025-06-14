package org.aom.movie_service.utils;

import static java.lang.Thread.sleep;

public class CommonUtil {

    /**
     * This method is used to introduce a delay in the execution of the program.
     * It is useful for simulating time-consuming operations or for testing purposes.
     *
     * @param ms The number of milliseconds to delay.
     */
    public static void delay(int ms){
        try {
            sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}