package io.github.llh4github.lotus.api

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

/**
 *
 * Created At 2023/12/29 22:49
 * @author llh
 */
@SpringBootApplication(scanBasePackages = ["io.github.llh4github.lotus.*"])
class ApiApp


fun main(args: Array<String>) {
    runApplication<ApiApp>(*args)
}
