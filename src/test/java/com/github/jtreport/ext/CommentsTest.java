//package com.github.jtreport.ext;
//
//import com.github.jtreport.annotations.TestCase;
//import com.github.jtreport.annotations.TestScenario;
//import com.github.jtreport.annotations.Automated;
//import com.github.jtreport.annotations.Manual;
//import japa.parser.JavaParser;
//import japa.parser.ast.Comment;
//import japa.parser.ast.LineComment;
//import japa.parser.ast.body.MethodDeclaration;
//import japa.parser.ast.expr.AnnotationExpr;
//import japa.parser.ast.visitor.VoidVisitorAdapter;
//import org.junit.Test;
//
//import java.io.FileInputStream;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
///**
// * Created by ka40215 on 8/13/14.
// */
//@TestScenario
//public class CommentsTest {
//
//    @Test
//    @Automated
//    @TestCase
//    public void testSample() throws Exception {
//        // creates an input stream for the file to be parsed
//        FileInputStream in = new FileInputStream("src/test/java/com/github/jtreport/ext/CommentsTest.java");
//
//        japa.parser.ast.CompilationUnit cu;
//
//        try {
//            // parse the file
//            cu = JavaParser.parse(in);
//        } finally {
//            in.close();
//        }
//
//        List<Comment> comments = cu.getComments();
//        CommentsVisitor commentsVisitor = new CommentsVisitor();
//        for (Comment comment:comments){
//            comment.accept(commentsVisitor,null);
//        }
//
//        MethodVisitor methodVisitor = new MethodVisitor();
//        methodVisitor.visit(cu,commentsVisitor.getCommentLocationsMap());
//
//        Map<String,String> map = methodVisitor.getMethodCommentsMap();
//
//        for(Map.Entry<String,String> entry:map.entrySet()){
//            System.out.println(entry.getKey()+"   "+entry.getValue());
//        }
//
//
//
//    }
//
//    @Test
//    @Manual
//    public void test1(){
//    }
//
//    @Test
//    public void test2(){
////tetsstt
//    }
//    private static class MethodVisitor extends VoidVisitorAdapter {
//        private Map<String, String> methodCommentsMap = new HashMap<String, String>();
//
//        @Override
//        public void visit(MethodDeclaration methodDeclaration, Object arg) {
//            Map<Integer, String> commentsLocationsMap = (Map<Integer,String>)arg;
//            String comments="";
//            List<AnnotationExpr> methodAnnotations = methodDeclaration.getAnnotations();
//            if (methodAnnotations !=null){
//                for (AnnotationExpr annotationExpr:methodAnnotations) {
//                    if (annotationExpr.getName().getName().equals("Test")) {
//
//                        for(Map.Entry<Integer,String> entry: commentsLocationsMap.entrySet()){
//                            int key = entry.getKey();
//                            if(key>= methodDeclaration.getBeginLine() && key <= methodDeclaration.getEndLine()){
//                                comments= comments + entry.getValue()+",";
//                            }
//                        }
//                        methodCommentsMap.put(methodDeclaration.getName(),comments);
//                    }
//                }
//            }
//        }
//
//        public Map<String,String> getMethodCommentsMap(){
//            return methodCommentsMap;
//        }
//    }
//
//    private static class CommentsVisitor extends VoidVisitorAdapter {
//        private Map<Integer,String> commentLocationsMap = new HashMap<Integer,String>();
//
//        public void visit(LineComment lineComment, Object arg) {
////            System.out.println(lineComment.getContent()+lineComment.getBeginLine());
//            commentLocationsMap.put(lineComment.getBeginLine(), lineComment.getContent());
//
//        }
//        public Map<Integer,String> getCommentLocationsMap(){
//            return commentLocationsMap;
//        }
//    }
//
//}
