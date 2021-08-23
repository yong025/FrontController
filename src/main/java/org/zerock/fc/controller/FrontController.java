package org.zerock.fc.controller;

import org.reflections.Reflections;
import org.reflections.scanners.MethodAnnotationsScanner;
import org.zerock.fc.annotations.GetMapping;
import org.zerock.fc.annotations.PostMapping;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.Set;

@WebServlet(name = "FrontController", urlPatterns = {"*.do"})
@MultipartConfig
public class FrontController extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("-------------------------------");

        request.setCharacterEncoding("UTF-8");

        String path = request.getRequestURI();
        System.out.println(path);

        Reflections reflections = new Reflections("org.zerock.fc.controller.sub", MethodAnnotationsScanner.class);

        String methodType = request.getMethod();

        if(methodType.equals("GET")){
            Set<Method> getMethods = reflections.getMethodsAnnotatedWith(GetMapping.class);
            Iterator<Method> getIt = getMethods.iterator();

            while(getIt.hasNext()){

                Method method = getIt.next();

                String value = method.getAnnotation(GetMapping.class).value();

                System.out.println(value);

                if(value.equals(path)){
                    System.out.println("find method success ");

                    Class<?> clz = method.getDeclaringClass();

                    System.out.println(clz);

                    try {
                        Object obj =clz.getConstructor(null).newInstance(null);
                        System.out.println(obj);

                        String result = (String) method.invoke(obj, request,response);
                        //obj객체의 request, response의 데이터값들을 스트링타입으로 다운캐스팅해달라.

                        if(result != null){
                            if(result.startsWith("re:")){
                                response.sendRedirect(result.substring(3));
                                break;
                            }else{
                                request.getRequestDispatcher("/WEB-INF/"+result+".jsp").forward(request,response);
                                break;
                            }
                        }


                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }//end if
            }//while
        }else {
            Set<Method> postMethods = reflections.getMethodsAnnotatedWith(PostMapping.class);

            Iterator<Method> postIt = postMethods.iterator();

            while(postIt.hasNext()){

                Method method = postIt.next();

                String value = method.getAnnotation(PostMapping.class).value();

                System.out.println(value);

                if(value.equals(path)){
                    System.out.println("find method success ");

                    Class<?> clz = method.getDeclaringClass();

                    System.out.println(clz);

                    try {
                        Object obj =clz.getConstructor(null).newInstance(null);
                        System.out.println(obj);

                        String result = (String) method.invoke(obj, request,response);


                        if(result != null){
                            if(result.startsWith("re:")){
                                response.sendRedirect(result.substring(3));
                                break;
                            }else{
                                request.getRequestDispatcher("/WEB-INF/"+result+".jsp").forward(request,response);
                                break;
                            }
                        }


                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }//end if
            }//while
        }





    }
}