<%--
    Document   : bacheca
    Created on : 7-set-2017, 9.44.48
    Author     : Ste2016
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" type="text/css" href="style.css" media="screen">
<!DOCTYPE html>
<html>
    <head>  
        <title>Bacheca</title>
        <meta charset="UTF-8">
        <meta name="description" content="Bacheca NerdBook">
        <meta name="author" content="Stefano Fenu">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
         
    </head>
    <body>
        <!-- aggiunto-->
        <c:set var="page" value="bacheca" scope="request"/>
        
        <!--Importa l'header della pagina-->
        <jsp:include page="header.jsp"/>

        <!--Importa la sezione a margine della pagina-->
        <jsp:include page="aside.jsp"/>  
               
        <div id="page">
            
            <!-- aggiunto-->
            <c:if test="${loggedIn}">
            
                <div id="frase">
                    
                    
                    <c:if test="${nome==null}">
                        <div>
                            <h2>${utenteLoggato.nome} ${utenteLoggato.cognome} </h2>
                        </div>
                    </c:if>
                    
                    
                    <c:if test="${nome!=null}">
                        <div>
                            <h2>${nome} ${utenteLoggato.cognome} </h2>
                            
                        </div>
                    </c:if>
                    
                    <c:if test="${cognome!=null}">
                        <div>
                            <h2>${utenteLoggato.nome} ${cognome} </h2>
                            
                        </div>
                    </c:if>
               
                    <c:if test="${frase!=null}">
                                <p>${frase}</p>
                    </c:if>   
                                
                    <c:if test="${frase==null}">
                          <p>${utenteLoggato.frase}</p>
                    </c:if>    
                          
                          
                    <c:if test="${utenteLoggato != bachecaUtente}">
                          <c:if test="${bachecaUtente!=null}">
                            <p>bacheca di: ${bachecaUtente.nome} ${bachecaUtente.cognome} </p>
                          </c:if>  
                          
                          <c:if test="${bachecaUtente==null}">
                            <p>bacheca di: ${bachecaGruppo.nome} </p>
                          </c:if>  
                      
                            
                    </c:if>        
                    
                    <%-- <div>
                        <h2>${utenteLoggato.nome} ${utenteLoggato.cognome} </h2>
                        <p>  ${utenteLoggato.frase}</p>
                    </div>
                    
                    
                     <c:if test="${frase!=null}">
                                <p>${frase}</p>
                            </c:if>   
                            <c:if test="${frase==null}">
                                <p>${utenteLoggato.frase}</p>
                            </c:if>    
                    --%>
                </div>
                
                <!-- aggiunto-->        
                <c:if test="${confermaPost!='7'}">    
                    
                    <!-- aggiunto-->
                    <div id="form">
                        <form action="bacheca.html?<c:if test="${utente!=null}">utente=${utente}</c:if><c:if test="${gruppo!=null}">gruppo=${gruppo}</c:if>" method="post"> <!-- metti get per evitare il refresh -->
                <!-- "bacheca.html? -->            
                            <div class="testo_input">
                                <input name="testo" type="text" placeholder="testo post">
                            </div>    

                            <div class="url_input">
                                <input  name="link" type="url" placeholder="inserisci url">
                            </div>  
                            
                            <div class="option">
                                <input type="radio" name="tipoAllegato" value="TESTO" checked>Text
                                <input type="radio" name="tipoAllegato" value="LINK">Link
                                <input type="radio" name="tipoAllegato" value="IMMAGINE">Image
                            </div>
                            
               
                            <div class="button">
                                
                                <button type="submit" name="confermaPost" value="7">  Carica </button>
                                
                                
                            </div>      
                           
                       </form>
                        
     
                    </div>    
                <!-- aggiunto-->
                </c:if>  

                                
               <c:if test="${confermaPost=='7'}">
                   
                   <c:if test="${utente!=null}">
                       <p>Bacheca di: ${bachecaUtente.nome} ${bachecaUtente.cognome}</p>
                   </c:if>

                   <c:if test="${gruppo!=null}">
                       <p>Bacheca di: ${bachecaGruppo.nome}</p>
                   </c:if>
                   
                   <div id="form">
                            <form action="bacheca.html?<c:if test="${utente!=null}">utente=${utente}</c:if><c:if test="${gruppo!=null}">gruppo=${gruppo}</c:if>" method="post">
                                    <input type="hidden" name="salvaAllegato" value="${contenutoAllegato}">
                                    <input type="hidden" name="salvaTesto" value="${testo}">
                                    <input type="hidden" name="salvaTipo" value="${tipoAllegato}">
                                     <h2>Hai condiviso un Post sulla bacheca di: ${bachecaUtente.nome} ${bachecaUtente.cognome}</h2>
                                     
                                    <button type="submit" id="invia" name="Ok" value="0"> Ok </button>
                            </form>
                    </div>            
                </c:if>  
                                
                <c:if test="${confermaPost=='0'}">
                            <c:if test="${utente!=null}">
                                <p>Hai scritto nella bacheca di ${bachecaUtente.nome} ${bachecaUtente.cognome}</p>
                            </c:if>
                            
                            <c:if test="${gruppo!=null}">
                                <p>Hai scritto nella bacheca di ${bachecaGruppo.nome}</p>
                            </c:if>
                </c:if> 
                                    
                                
            
                            
        <div id="_post">                        
                                
              <%-- aggiunto: bacheca utente loggato --%>              
              <c:forEach var="post" items="${posts}">  
                        <div class="post">
                            
                            <div id="autore">
                                <img class="immagine" title="profilo" alt="Foto Autore" src="${post.autore.urlFoto}" width="60" height="60">

                                <div id="nome">
                                       <h3> ${post.autore.nome} ${post.autore.cognome} </h3>
                                </div>
                            </div>
                                
                           <!-- <div class="infoUtente">
                                <div class="fotoProfilo">
                            <%-->         <img alt="Foto Profilo Utente" src="${post.autore.urlFoto}"> --%> 
                                </div>
                           <%-- aggiunto      <p>${post.autore.nome} ${post.autore.cognome}</p>  --%> 
                            </div>
                           -->
                           
                            <%--Ho fatto in modo che nonostante il post possa avere 3 tipi ben distinti (testo,link,immagine)
                                si possa in ogni caso inserire del testo --%> 
            
                            <div class="contentuto">
                                <p>
                                    
                                    <p>${post.contenuto}</p>
                                    
                                    <%--<c:if test="${post.type=='TESTO'}">
                                        <p>${post.contenuto}</p>
                                    </c:if>
                                    --%>
                                    
                                    <c:if test="${post.type=='LINK'}">
                                        <a href="${post.allegato}">${post.allegato}</a>
                                    </c:if>
                                               
                                        
                                </p>
                                <c:if test="${post.type=='IMMAGINE'}">
                                    
                                        <img id="postImg" alt="Foto post utente" src="${post.allegato}" width="100" height="100">
                                    
                                </c:if>
                            </div>
                        </div>
                    </c:forEach>
                </div>


            </c:if>
           
            <c:if test="${loggedIn==null}">
                <h2> Utente non loggato </h2>
            </c:if>               
                            
       
        
        
        
    </body>
</html>
                       
                        
   <!--                   
                <div class="post">

                    <div id="autore">
                        <img class="immagine" title="profilo" alt="Foto Stefano" src="imgSTE.jpg" width="60" height="60">

                        <div id="nome">
                            <h3> Ezio Greggio </h3>
                        </div>
                    </div>

                    <div id="testo">
                        <p> Le Velineeeeeeee!!</p>      
                    </div>

                    <div class="contenuto">

                        <a href="https://mediaset.it"> https://mediaset.it </a>

                    </div>

                </div>    

   <%-- </c:if> --%>
                
                 <div class="post">

                    <div id="autore">
                        <img class="immagine" title="profilo" alt="Foto Ayrton" src="ayrt.jpg" width="60" height="60">

                        <div id="nome">
                            <h3> Ayrton Sanna </h3>
                        </div>

                    </div>

                    <div id="testo">
                        <p> Accellèra!</p>      
                    </div>

                    <div class="contenuto">

                        <img class="immaginep" title="ayrt.jpg" alt="Ayrton" src="2010160-1-266x77.jpg" width="266" height="77">

                    </div>

                </div>    


                 <div class="post">

                    <div id="autore">

                        <img class="immagine" title="profilo" alt="Foto Mario" src="mario.jpg" width="60" height="60">

                        <div id="nome">
                            <h3> Mario Più </h3>
                        </div>


                    </div>

                    <div id="testo">
                        <p> Come è nata la musica elettronica?</p>      
                    </div>

                    <div class="contenuto">



                    </div>

                </div>    



               </div>
 -->               
        <!-- aggiunto-->
   