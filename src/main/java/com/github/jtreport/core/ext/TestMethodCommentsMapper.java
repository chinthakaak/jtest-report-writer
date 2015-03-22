package com.github.jtreport.core.ext;

import japa.parser.JavaParser;
import japa.parser.ast.Comment;
import japa.parser.ast.CompilationUnit;
import japa.parser.ast.LineComment;
import japa.parser.ast.body.MethodDeclaration;
import japa.parser.ast.expr.AnnotationExpr;
import japa.parser.ast.visitor.VoidVisitorAdapter;
import org.apache.commons.lang.StringUtils;
import org.junit.runner.Description;

import java.io.FileInputStream;
import java.util.*;

/**
 * Created by ka40215 on 8/13/14.
 */
public class TestMethodCommentsMapper {
    public String getMethodComments(String className, String methodName){
        return null;
    }
    public Map<String, String> getMethodCommentsMap(){
        return null;
    }

    public String getTestSteps(Description description) throws Exception {
//        System.out.println(description.getClassName().replace(".", "/"));
        String path = description.getClassName().replace(".", "/");

        String sourceFile = "src/test/java/" + path + ".java";

        // creates an input stream for the file to be parsed
        FileInputStream in = new FileInputStream(sourceFile);

        CompilationUnit cu;

        try {
            // parse the file

            cu = JavaParser.parse(in);

        } finally {
            in.close();
        }

        List<Comment> comments = cu.getComments();
//        System.out.println(comments.size());
        CommentsVisitor commentsVisitor = new CommentsVisitor();

        if (comments!=null) {
            for (Comment comment : comments) {
                comment.accept(commentsVisitor, null);
            }
        }

        MethodVisitor methodVisitor = new MethodVisitor();
        methodVisitor.visit(cu, commentsVisitor.getCommentLocationsMap());

        Map<String, String> map = methodVisitor.getMethodCommentsMap();

        return map.get(description.getMethodName());
    }
    private static class MethodVisitor extends VoidVisitorAdapter {
        private Map<String, String> methodCommentsMap = new HashMap<String, String>();

        @Override
        public void visit(MethodDeclaration methodDeclaration, Object arg) {
            Map<Integer, String> commentsLocationsMap = (Map<Integer,String>)arg;
//            String comments="";
            String delim="";
            StringBuilder builder = new StringBuilder();
            List<AnnotationExpr> methodAnnotations = methodDeclaration.getAnnotations();
            if (methodAnnotations !=null){
                for (AnnotationExpr annotationExpr:methodAnnotations) {
                    if (annotationExpr.getName().getName().equals("Test")) {

                        for(Map.Entry<Integer,String> entry: commentsLocationsMap.entrySet()){
                            int key = entry.getKey();
                            if((key>= methodDeclaration.getBeginLine()) && (key <= methodDeclaration.getEndLine())){
                                builder.append(delim).append(entry.getValue());
                                delim="|";
//                                System.out.println(commentsLocationsMap.values());
//                                System.out.println(commentsLocationsMap.size());
//                                comments= comments + entry.getValue()+",";
                            }
                        }
                        String tempSteps = builder.toString().replaceAll("[\\r\\n]+", "");
                        String[] steps= StringUtils.split(tempSteps,"|");
                        builder.setLength(0);
                        for (int i =1 ;i<=steps.length;i++){
                            builder.append(i+"."+steps[i-1]+"\n\r");
                        }
                        methodCommentsMap.put(methodDeclaration.getName(),builder.toString());

                    }
                }
            }
        }

        public Map<String,String> getMethodCommentsMap(){
            return methodCommentsMap;
        }
    }

    private static class CommentsVisitor extends VoidVisitorAdapter {
        private Map<Integer,String> commentLocationsMap = new TreeMap<Integer,String>();

        public void visit(LineComment lineComment, Object arg) {
//            System.out.println(lineComment.getContent()+lineComment.getBeginLine());
            commentLocationsMap.put(lineComment.getBeginLine(), lineComment.getContent());

        }
        public Map<Integer,String> getCommentLocationsMap(){
            return commentLocationsMap;
        }
    }
}
