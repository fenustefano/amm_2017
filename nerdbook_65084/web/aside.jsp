<%-- 
    Document   : aside
    Created on : 6-set-2017, 16.26.52
    Author     : Ste2016
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" type="text/css" href="style.css" media="screen">
 
<aside>
         <div class="cerca">   
                <div>
                    <input type="search" id="search" placeholder="cerca">
                </div>     
         </div>
            
            <div class="persone">
                
                <div id="titolo_persone">
                    <h3> Persone </h3>    
                </div>
                
                <div id="lista">
                    <c:forEach var="utente" items="${listaUtenti}">
                    <li>
                        <div class="fotoProfilo sidebarFoto">
                            <img alt="Foto Profilo Utente" src="${utente.urlFoto}" width="15" height="15">
                        </div>
                        <a href="bacheca.html?utente=${utente.id}">${utente.nome} ${utente.cognome}</a>
                    </li>
                </c:forEach>
                </div>  
            </div>
            
            
            <div class="gruppi">
                
                <div id="titolo_gruppi">
                    <h3> Gruppi </h3>    
                </div>
                
                <div id="lista_gr">
                    <c:forEach var="gruppo" items="${listaGruppi}">
                    <li>
                        <div class="fotoProfilo sidebarFoto">
                                <img alt="Foto Gruppo" src="${gruppo.urlFoto}" width="15" height="15">
                        </div>
                        <a href="bacheca.html?gruppo=${gruppo.id}">${gruppo.nome}</a>
                    </li>
                </c:forEach>
                </div>
                
                
            </div>
             
        </aside> 









