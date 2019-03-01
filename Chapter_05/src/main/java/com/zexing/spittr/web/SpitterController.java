package com.zexing.spittr.web;

import com.zexing.spittr.bean.Spitter;
import com.zexing.spittr.repository.SpittlerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import org.springframework.validation.Errors;

@Controller
@RequestMapping("/spittler")
public class SpitterController {
    /**
     *
     * Created by Joson on 2/28/2019.
     */
    private SpittlerRepository spittlerRepository;

    @Autowired
    public SpitterController(SpittlerRepository spittlerRepository){
        this.spittlerRepository = spittlerRepository;
    }

    @RequestMapping(value = "/register",method = RequestMethod.GET)
    public String showRegistrationForm(){
        return "registerForm";
    }

    @RequestMapping(value = "/register",method = RequestMethod.POST)
    public String processRegistration(@Valid Spitter spitter,Errors errors){
        if (errors.hasErrors()) {
            return "registerForm";
        }
        spittlerRepository.save(spitter);
        return "redirect:/spitter/"+ spitter.getUsername();
    }


    @RequestMapping(value = "/profile/{userName}")
    public String showSpitterProfile(@PathVariable String userName, Model model){   //这里展现user的信息，需要模型数据传递给视图
        model.addAttribute(spittlerRepository.findSpittlerByUserName(userName));
        return "profile";
    }

}