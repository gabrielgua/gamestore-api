package com.gabriel.gamestore.api.security.roleauthotization;

import org.springframework.security.access.prepost.PreAuthorize;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

public @interface CheckSecurity {

    public @interface Geral {

        @Retention(RetentionPolicy.RUNTIME)
        @Target(ElementType.METHOD)
        @PreAuthorize("permitAll()")
        public @interface podeConsultar {}

        @Retention(RetentionPolicy.RUNTIME)
        @Target(ElementType.METHOD)
        @PreAuthorize("@authorizationConfig.podeGerenciarOuConsultarRecursosProtegidosGerais()")
        public @interface podeGerenciar {}

        @Retention(RetentionPolicy.RUNTIME)
        @Target(ElementType.METHOD)
        @PreAuthorize("@authorizationConfig.isAdmin")
        public @interface isAdmin {}

    }

    public @interface Usuarios {

        @Retention(RetentionPolicy.RUNTIME)
        @Target(ElementType.METHOD)
        @PreAuthorize("@authorizationConfig.podeGerenciarOuConsultarRecursosProtegidosGerais()")
        public @interface podeConsultar {}

        @Retention(RetentionPolicy.RUNTIME)
        @Target(ElementType.METHOD)
        @PreAuthorize("@authorizationConfig.podeBuscarUsuario(#usuarioId)")
        public @interface podeBuscar {}


        @Retention(RetentionPolicy.RUNTIME)
        @Target(ElementType.METHOD)
        @PreAuthorize("@authorizationConfig.podeGerenciarUsuario(#usuarioId)")
        public @interface podeGerenciar {}

        @Retention(RetentionPolicy.RUNTIME)
        @Target(ElementType.METHOD)
        @PreAuthorize("@authorizationConfig.podeAlterarPropriaSenha(#usuarioId)")
        public @interface podeAlterarPropriaSenha {}

        @Retention(RetentionPolicy.RUNTIME)
        @Target(ElementType.METHOD)
        @PreAuthorize("@authorizationConfig.usuarioAutenticadoIgualA(#usuarioId)")
        public @interface isUsuarioAutenticado {}

        @Retention(RetentionPolicy.RUNTIME)
        @Target(ElementType.METHOD)
        @PreAuthorize("@authorizationConfig.podeGerenciarPermissoesAdmin(#usuarioId)")
        public @interface podeGerenciarPermissoesAdmin {}

    }

    public @interface UsuarioJogos {

        @Retention(RetentionPolicy.RUNTIME)
        @Target(ElementType.METHOD)
        @PreAuthorize("@authorizationConfig.podeListarJogosDoUsuario(#usuarioId)")
        public @interface podeListar {}

        @Retention(RetentionPolicy.RUNTIME)
        @Target(ElementType.METHOD)
        @PreAuthorize("@authorizationConfig.podeGerenciarJogosDoUsuario(#usuarioId)")
        public @interface podeGerenciar {}


    }

    public @interface Pedidos {


        @Retention(RetentionPolicy.RUNTIME)
        @Target(ElementType.METHOD)
        @PreAuthorize("@authorizationConfig.podeGerenciarOuConsultarRecursosProtegidosGerais()")
        public @interface podeConsultar {}

        @Retention(RetentionPolicy.RUNTIME)
        @Target(ElementType.METHOD)
        @PreAuthorize("@authorizationConfig.podeConsultarPropriosPedidos(#usuarioId)")
        public @interface podeConsultarPropriosPedidos {}

        @Retention(RetentionPolicy.RUNTIME)
        @Target(ElementType.METHOD)
        @PreAuthorize("@authorizationConfig.podeGerenciarOuConsultarRecursosProtegidosGerais()")
        public @interface podeGerenciar {}

        @Retention(RetentionPolicy.RUNTIME)
        @Target(ElementType.METHOD)
        @PreAuthorize("@authorizationConfig.podeAdicionarPedido()")
        public @interface podeAdicionar {}

        @Retention(RetentionPolicy.RUNTIME)
        @Target(ElementType.METHOD)
        @PreAuthorize("@authorizationConfig.podeConfirmarPedido(#codigoPedido)")
        public @interface podeConfirmarPedido {}

        @Retention(RetentionPolicy.RUNTIME)
        @Target(ElementType.METHOD)
        @PreAuthorize("@authorizationConfig.podeCancelarPedido(#codigoPedido)")
        public @interface podeCancelarPedido {}

        @Retention(RetentionPolicy.RUNTIME)
        @Target(ElementType.METHOD)
        @PreAuthorize("@authorizationConfig.podeReembolsarPedido(#codigoPedido)")
        public @interface podeReembolsarPedido {}
    }


}
