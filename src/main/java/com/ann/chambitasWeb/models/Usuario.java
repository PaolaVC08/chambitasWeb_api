package com.ann.chambitasWeb.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "usuarios",
       uniqueConstraints = {
            @UniqueConstraint(columnNames = "correo")
})
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Long id;

    @NotBlank
    @Size(max = 20)
    private String nombre;

    @NotBlank
    @Size(max = 20)
    @Column(name = "apellido_paterno")
    private String apellidoPaterno;

    @NotBlank
    @Size(max = 20)
    @Column(name = "apellido_materno")
    private String apellidoMaterno;

    @NotNull
    @Past
    @Column(name = "fecha_nacimiento")
    private LocalDate fechaNacimiento;

    @NotBlank
    @Size(max=50)
    @Email
    private String correo;

    @NotBlank
    @Size(max=120)
    private String password;

    @Column(name = "foto_perfil_b64")
    private String fotoPerfilB64;

    @Enumerated(EnumType.STRING)
    private EstadoUsuario estado = EstadoUsuario.ACTIVO;

     @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(name = "usuario_rol", joinColumns = @JoinColumn(name = "usuario_id"), inverseJoinColumns = @JoinColumn(name = "id_rol"))
  private Set<Rol> roles = new HashSet<>();

  public Usuario() {
    }

    public Usuario(String nombre, @NotBlank @Size(max = 20) String apellidoPaterno,
        @NotBlank @Size(max = 20) String apellidoMaterno, @NotBlank @Size(max = 20) LocalDate fechaNacimiento,
        @NotBlank @Size(max = 50) @Email String correo, @NotBlank @Size(max = 120) String password,
        String fotoPerfilB64, EstadoUsuario estado, Set<Rol> roles) {
    
    this.nombre = nombre;
    this.apellidoPaterno = apellidoPaterno;
    this.apellidoMaterno = apellidoMaterno;
    this.fechaNacimiento = fechaNacimiento;
    this.correo = correo;
    this.password = password;
    this.fotoPerfilB64 = fotoPerfilB64;
    this.estado = estado;
    this.roles = roles;
}

//GETTERS AND SETTERS
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFotoPerfilB64() {
        return fotoPerfilB64;
    }

    public void setFotoPerfilB64(String fotoPerfilB64) {
        this.fotoPerfilB64 = fotoPerfilB64;
    }

    public EstadoUsuario getEstado() {
        return estado;
    }

    public void setEstado(EstadoUsuario estado) {
        this.estado = estado;
    }
    public Set<Rol> getRoles() {
        return roles;
      }
    
      public void setRoles(Set<Rol> roles) {
        this.roles = roles;
      }
    
}


