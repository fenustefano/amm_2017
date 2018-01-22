<%-- 
    Document   : profilo
    Created on : 18-set-2017, 17.41.34
    Author     : Ste2016
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Profilo</title>
        <meta charset="UTF-8">
        <meta name="description" content="Profilo utente NerdBook">
        <meta name="author" content="Stefano Fenu">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" type="text/css" href="style.css"
              media="screen">
    </head>
    <body>

        <c:set var="page" value="profilo" scope="request"/>
        <jsp:include page="header.jsp"/>



        <div class="page">

            <c:if test="${loggedIn}"> 
                <jsp:include page="aside.jsp"/>


                <c:if test="${cambio=='7'}">
                    <p>Dati già modificati</p>
                </c:if>



                <form method="post" action="profilo.html">
                    <label for="nm">Nome</label>

                    <c:if test="${nome!=null}"> 
                        <input name="nomeUtente" id="nm" type="text" value="${nome}"/>
                    </c:if>

                    <c:if test="${nome==null}"> 
                        <input name="nomeUtente" id="nm" type="text" value="${utenteLoggato.nome}"/>
                    </c:if>

                    <br><br>

                    <label for="cognome">Cognome</label>

                    <c:if test="${cognome!=null}"> 
                        <input name="cognome" id="cognome" type="text"value="${cognome}"/>
                    </c:if>

                    <c:if test="${cognome==null}"> 
                        <input name="cognome" id="cognome" type="text"value="${utenteLoggato.cognome}"/>
                    </c:if>

                    <br><br>

                    <label for="imgurl">Url immagine profilo</label>

                    <c:if test="${url!=null}"> 
                        <input name="cognome" id="cognome" type="text"value="${url}"/>
                    </c:if>

                    <c:if test="${url==null}"> 
                        <input name="UrlImg" id="imgurl" type="text" value="${utenteLoggato.urlFoto}"/>
                    </c:if>

                    <br><br>

                    <label for="Descrizione">Frase Profilo</label>

                    <c:if test="${frase!=null}"> 
                        <textarea rows="4" cols="20"
                                  name="descrizione" id="descrizione" value="${frase}" >${frase}
                        </textarea>

                    </c:if>

                    <c:if test="${frase==null}"> 
                        <textarea rows="4" cols="20"
                                  name="descrizione" id="descrizione" value="${utenteLoggato.frase}" > ${utenteLoggato.frase}
                        </textarea>
                    </c:if>

                    <div class="buttonP">

                        <button type="submit" name="aggiorna" value="7"> Aggiorna </button>

                        <button type="submit" name="cancella" value="1">Cancella Profilo</button>

                    </div>    
                </form>


            </c:if>        
        </div>           
        <c:if test="${loggedIn==null}">
            <h2>Utente non loggato</h2>
        </c:if>        



    </body>
</html>

