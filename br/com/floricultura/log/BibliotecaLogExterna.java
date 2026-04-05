package br.com.floricultura.log;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Simula uma biblioteca de log externa com API incompatível com a nossa interface.
 * No mundo real, este seria um jar de terceiros (ex: Log4j, SLF4J, etc).
 *
 * Esta classe é o "Adaptee" do padrão Adapter.
 */
public class BibliotecaLogExterna {

    private static final DateTimeFormatter FMT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    // API diferente: usa "writeInfo", "writeWarn", "writeError" em vez de info/aviso/erro
    public void writeInfo(String tag, String msg) {
        System.out.printf("[%s] INFO  [%s] %s%n", agora(), tag, msg);
    }

    public void writeWarn(String tag, String msg) {
        System.out.printf("[%s] WARN  [%s] %s%n", agora(), tag, msg);
    }

    public void writeError(String tag, String msg) {
        System.out.printf("[%s] ERROR [%s] %s%n", agora(), tag, msg);
    }

    private String agora() {
        return LocalDateTime.now().format(FMT);
    }
}
