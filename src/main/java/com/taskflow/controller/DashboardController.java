package com.taskflow.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Uygulamanın basit HTML tabanlı kullanıcı arayüzünü yöneten controller sınıfıdır.
 * <p>Statik sayfa yönlendirmesi yapar.</p>
 */
@Controller
public class DashboardController {

    /**
     * Kullanıcıyı dashboard.html sayfasına yönlendirir.
     *
     * @return dashboard şablonunun adı
     */
    @GetMapping("/dashboard")
    public String dashboard() {
        return "dashboard"; // resources/templates/dashboard.html sayfasına gider
    }
}
