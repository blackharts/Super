package com.supertec.Controllers;

import com.supertec.Clases.Cliente;
import com.supertec.JpaController.ClienteJpaController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ClienteController {

@Autowired ClienteJpaController cliJpa;
  
    @RequestMapping("/cliente")
    public String showCliente(Model model, @ModelAttribute("result") String result) {
        Cliente cliente = new Cliente();
        model.addAttribute("cliente", cliente );
          model.addAttribute("result", result );
        return "cliente";
    }
     @RequestMapping(value="/cliente/save",method=RequestMethod.POST)
    public String handleCliente(@ModelAttribute("cliente") Cliente clienteForm,Model model,
            RedirectAttributes red) {
        red.addFlashAttribute("result","Se ha registrado Exitosamente!");
       
        return "redirect:/index";
    }
     @RequestMapping("/cliente/{id}/update")//metodo para editar
 	public String showUpdate(Model model,
 			@PathVariable("id") int id) {
 		
 		
 		Cliente cliente = cliJpa.findCliente(id);
 		model.addAttribute("cliente", cliente);
 		
 		return "cliente";
 	}
}
