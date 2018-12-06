package com.supertec.Controllers;

import com.supertec.JpaController.ClienteJpaController;
import com.supertec.clases.Cliente;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.springframework.beans.propertyeditors.CustomDateEditor;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller

public class ClienteController {

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/mm/yyyy");
        sdf.setLenient(true);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
    }

    @RequestMapping("/cliente")
    public String showCliente(Model model, @ModelAttribute("result") String result) {
        Cliente cliente = new Cliente();
        model.addAttribute("cliente", cliente);
        model.addAttribute("result", result);
        return "cliente";
    }

    @RequestMapping(value = "/cliente/save", method = RequestMethod.POST)
    public String handleCliente(@ModelAttribute("cliente") Cliente clienteForm, Model model,
            RedirectAttributes red) {
        red.addFlashAttribute("result", "Se ha registrado Exitosamente!");

        return "redirect:/index";
    }

    @RequestMapping(value = "/cliente_save", method = RequestMethod.POST)
    public String handleSave(
            @RequestParam("nombre") String nombre,
            @RequestParam("usuario") String usuario,
            @RequestParam("rut") String rut,
            @RequestParam("correo") String correo,
            @RequestParam("telefono") String telefono,
            @RequestParam("contrasenia") String contrasenia,
           // @RequestParam("fechaNacimiento") Date fechaNacimiento,
            Model model) throws Exception {

        if (nombre.trim().equals("")) {
            return "error500";
        } else {

            Cliente cli = new Cliente();
            cli.setNombre(nombre);
            cli.setRut(rut);
            cli.setUsuario(usuario);
            cli.setCorreo(correo);
            cli.setContrasenia(contrasenia);
           // cli.setFechaNacimiento(fechaNacimiento);
            cli.setTelefono(telefono);

            EntityManagerFactory emf = Persistence.createEntityManagerFactory("com.supertec_Supertec_war_1.0-SNAPSHOTPU");
            ClienteJpaController cl = new ClienteJpaController(emf);

            cl.create(cli);

            model.addAttribute("cliente", cl);
            return "index";
        }

    }
}
