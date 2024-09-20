package com.example.demospring;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@RequestMapping(value = "/reniec")
public class DemoSpringApplication {

    private static final Map<String, Map<String, String>> dniDatabase = new HashMap<>();

    static {
        inicializarBaseDeDatos();
    }

    public static void main(String[] args) {
        SpringApplication.run(DemoSpringApplication.class, args);
    }

    @GetMapping("/buscar/{dni}")
    public ResponseEntity<Map<String, String>> buscarPorDNI(@PathVariable String dni) {
        Map<String, String> persona = dniDatabase.get(dni);
        if (persona != null) {
            return ResponseEntity.ok(persona);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/todos")
    public ResponseEntity<List<Map<String, String>>> obtenerTodos() {
        return ResponseEntity.ok(new ArrayList<>(dniDatabase.values()));
    }

    private static void inicializarBaseDeDatos() {
        agregarPersona("12345678", "Juan", "Pérez López", "Av. Arequipa 123", "150101", "1990-05-15");
        agregarPersona("87654321", "María", "González Rodríguez", "Jr. Huallaga 456", "150102", "1985-10-20");
        agregarPersona("23456789", "Carlos", "Martínez Silva", "Calle Los Pinos 789", "150103", "1992-03-25");
        agregarPersona("98765432", "Ana", "López García", "Av. La Marina 234", "150104", "1988-12-30");
        agregarPersona("34567890", "Pedro", "Ramírez Torres", "Jr. Cusco 567", "150105", "1995-07-05");
        agregarPersona("09876543", "Lucía", "Fernández Castro", "Av. Brasil 890", "150106", "1993-09-10");
        agregarPersona("45678901", "Diego", "Torres Vargas", "Calle Las Flores 123", "150107", "1987-02-18");
        agregarPersona("10987654", "Sofía", "Chávez Rojas", "Jr. Ayacucho 456", "150108", "1991-11-22");
        agregarPersona("56789012", "Miguel", "Rojas Mendoza", "Av. Tacna 789", "150109", "1989-04-07");
        agregarPersona("21098765", "Carmen", "Vargas Flores", "Calle Los Alamos 234", "150110", "1994-08-12");
    }

    private static void agregarPersona(String dni, String nombres, String apellidos, String direccion, String ubigeo, String fechaNacimiento) {
        Map<String, String> persona = new HashMap<>();
        persona.put("dni", dni);
        persona.put("nombres", nombres);
        persona.put("apellidos", apellidos);
        persona.put("direccion", direccion);
        persona.put("ubigeo", ubigeo);
        persona.put("fechaNacimiento", fechaNacimiento);
        dniDatabase.put(dni, persona);
    }
}
