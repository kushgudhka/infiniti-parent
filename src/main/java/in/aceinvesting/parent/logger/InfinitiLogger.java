package in.aceinvesting.parent.logger;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author: Kush Gudhka
 */
@Slf4j
public class InfinitiLogger {
    public static void info(String message, Object... args) {
        log.info("INFINITI : " + message, args);
    }

    public static void error(String message, Object... args) {
        log.error("INFINITI : " + message, args);
    }

    public static void warn(String message, Object... args) {
        log.warn("INFINITI : " + message, args);
    }
}
