package tn.projetdemo.demo.controllers;


import java.util.Date;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


import org.springframework.web.bind.annotation.RestController;



import tn.projetdemo.demo.dto.AuthResponseDTO;
import tn.projetdemo.demo.dto.LoginDto;
import tn.projetdemo.demo.dto.RegisterDto;

import tn.projetdemo.demo.entities.User;
import tn.projetdemo.demo.repository.UserRepository;
import tn.projetdemo.demo.security.JwtGenerator;
import tn.projetdemo.demo.services.UserServiceImpl;
import org.springframework.security.core.Authentication;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;



@RestController

@CrossOrigin(origins = "*")
public class UserController {
	@Autowired
	UserServiceImpl userServ;
	
	private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
   
    private final PasswordEncoder passwordEncoder;
    private final JwtGenerator jwtGenerator;

   
    public UserController(AuthenticationManager authenticationManager, UserRepository userRepository,
                           PasswordEncoder passwordEncoder, JwtGenerator jwtGenerator
                          ) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
       
        this.passwordEncoder = passwordEncoder;
        this.jwtGenerator = jwtGenerator;
    }
    
    @PostMapping("/api/auth/login")
    public ResponseEntity<?> login(@RequestBody LoginDto loginDto) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginDto.getUsername(),
                            loginDto.getPass_ut()
                    )
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);
            String token = jwtGenerator.generateToken(authentication);
            User user = userRepository.findByUsername(loginDto.getUsername()).orElse(null);
            if (user == null) {
                return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
            }

            // Retourner les informations de l'utilisateur avec le jeton d'authentification
            AuthResponseDTO responseDTO = new AuthResponseDTO(token);

            responseDTO.setAccessToken(token);
            responseDTO.setUser(user);

            return new ResponseEntity<>(responseDTO, HttpStatus.OK);

           

        } catch (AuthenticationException e) {
            // Authentication failed, return an error message
            return new ResponseEntity<>("Invalid username or password", HttpStatus.UNAUTHORIZED);
        }
    }
    
    
    @PostMapping("/api/auth/register")
    public ResponseEntity<String> register(@RequestBody RegisterDto registerDto) {
        

        User user = new User();
        user.setPrenom_ut(registerDto.getPrenom_ut());
        user.setNom_ut(registerDto.getNom_ut());
        user.setUsername(registerDto.getUsername());
        user.setEmail(registerDto.getEmail());
        user.setPass_ut(passwordEncoder.encode(registerDto.getPass_ut()));
        user.setCreated(new Date());
        user.setRolename(registerDto.getRolename());

 


        

        userRepository.save(user);


        return new ResponseEntity<>("User registered successfully!.", HttpStatus.OK);
    }
	
	@PostMapping(value = "/adduser")
	public User addUser(@RequestBody User user) {
		return userServ.addUser(user);
	}
    @PostMapping(value = "/addListUser")
    public List<User> addListUser(@RequestBody List<User> listuser){
    	return userServ.addListUser(listuser);
    }
    
    @PostMapping(value = "/addUserWTCP")
    public String addUserWTCP(@RequestBody User user){
    	return userServ.addUserWTCP(user);
    }
    @PostMapping(value = "/addUserWTUN")
    public String addUserWTUN(@RequestBody User user){
    	return userServ.addUserWTUN(user);
    }
    
    @PutMapping(value = "/updateUser/{id}")
    public User updateUser(@PathVariable Long id,@RequestBody User user){
    	
    	return userServ.updateUser(id,user);
    }
    
    @DeleteMapping(value="/deleteuser/{id}")
    public void deleteUser (@PathVariable Long id) {
    	 userServ.deleteUser(id);
    }
    @GetMapping(value="/getAllUserss")
    public List<User> getAllUsers(){
    	 return userServ.getListUsers();
    }
    @GetMapping(value="/getUserByUN/{un}")
    public User getUserByUN(@PathVariable String un){
    	 return userServ.getByUsername(un);
    }
    @GetMapping(value="/getUsersSWSUN/{un}")
    public List<User> getUsersSWSUN(@PathVariable String un){
    	 return userServ.getUsersSWSUN(un);
    }
    @GetMapping(value="/getUserById/{id}")
	public User getEvenementById(@PathVariable Long id) {
	    return userServ.getUserById(id);
	}
    
    
    
  
    
    
	

	
}
