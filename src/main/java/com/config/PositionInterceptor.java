package com.config;

import com.service.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class PositionInterceptor implements HandlerInterceptor {

    @Autowired
    private AuthService authService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestURI = request.getRequestURI();
        
        // Pular verificação para rotas excluídas
        if (requestURI.contains("/auth/login") || 
            requestURI.contains("/auth/refresh") || 
            requestURI.contains("/stock/take")) {
            return true;
        }

        String token = (String) request.getAttribute("jwtToken");
        if (token != null) {
            try {
                Integer positionId = authService.extractPositionId(token);
                if (positionId != null && positionId >= 3) {
                    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                    return false;
                }
            } catch (Exception e) {
                // Token inválido será tratado pelo filtro de autenticação
            }
        }
        
        return true;
    }
}