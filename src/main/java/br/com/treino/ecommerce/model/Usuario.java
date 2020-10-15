package br.com.treino.ecommerce.model;

import br.com.treino.ecommerce.encoder.SenhaEncoder;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.util.Assert;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.*;
import java.time.LocalDateTime;

@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private @Email @NotBlank String login;
    @Size(min = 6 , message = "A senha deve ter no mínimo 6 caracteres")
    private @NotBlank String senha;
    @JsonFormat(pattern = "dd-MM-yyyy'T'hh:mm:ss", shape = JsonFormat.Shape.STRING)
    private @NotNull LocalDateTime instanteCriacao;

    @Deprecated
    public Usuario(){}

    public Usuario(@Email @NotBlank String login,
                   @Size(min = 6, message = "A senha deve ter no mínimo 6 caracteres")
                   @NotBlank SenhaEncoder senhaLimpa) { //1

        //provocando exception, nesse caso correto?
        //Assert.isTrue(login.isBlank(), "O email não pode ser nulo");
        //Assert.notNull(senhaLimpa,"O objeto tipo SenhaLimpa não pode ser nulo");

        this.login = login;
        this.senha = senhaLimpa.encodar();
        this.instanteCriacao = LocalDateTime.now();
    }

    public String getLogin() {
        return login;
    }

    public String getSenha() {
        return senha;
    }

    public LocalDateTime getInstanteCriacao() {
        return instanteCriacao;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", senha='" + senha + '\'' +
                ", instanteCriacao=" + instanteCriacao +
                '}';
    }

}