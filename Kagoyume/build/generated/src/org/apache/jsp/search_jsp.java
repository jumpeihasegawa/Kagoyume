package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import com.fasterxml.jackson.databind.JsonNode;

public final class search_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");

    JsonNode searchResultJson = (JsonNode) request.getAttribute("searchResultJson");
    int seachNum = searchResultJson.get("ResultSet").get("totalResultsReturned").intValue();

      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <title>検索結果</title>\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("        ");
//検索キーワード、検索結果数を表示
      out.write("\n");
      out.write("        検索キーワード：\n");
      out.write("        ");
out.print(searchResultJson.get("ResultSet").get("0").get("Result").get("Request").get("Query").textValue());
      out.write("\n");
      out.write("        検索結果数：\n");
      out.write("        ");
out.print(searchResultJson.get("ResultSet").get("totalResultsReturned").intValue());
      out.write("\n");
      out.write("        <br><br>\n");
      out.write("\n");
      out.write("        ");
//結果は上位20件まで
      out.write("\n");
      out.write("        ");
for(int num = 0;num < seachNum;num++){
      out.write("\n");
      out.write("        ");
String seachNumStr = String.valueOf(num);
      out.write("\n");
      out.write("        ");
//縦のリスト型に表示。サムネイルと、その横に商品名、金額が載っている。クリックでitemへ
      out.write("\n");
      out.write("        <form action=\"item\" method=\"GET\">\n");
      out.write("            <table>\n");
      out.write("                <tr>サムネイル：\n");
      out.write("                <img src=\"");
out.print(searchResultJson.get("ResultSet").get("0").get("Result").get(seachNumStr).get("Image").get("Small").textValue());
      out.write("\"></tr>\n");
      out.write("                ");
//商品IDをGETメソッドにより受け渡す
      out.write("\n");
      out.write("                <tr>商品名:\n");
      out.write("                    ");
out.print(searchResultJson.get("ResultSet").get("0").get("Result").get(seachNumStr).get("Name").textValue());
      out.write("</tr>\n");
      out.write("                <tr>金額:\n");
      out.write("                    ");
out.print(searchResultJson.get("ResultSet").get("0").get("Result").get(seachNumStr).get("Price").get("_value").textValue());
      out.write("円</tr>\n");
      out.write("                <tr>\n");
      out.write("                <input type=\"hidden\" name=\"商品ID\" value=\"");
searchResultJson.get("ResultSet").get("0").get("Result").get(seachNumStr).get("ProductId");
      out.write("\">\n");
      out.write("                <input type=\"submit\" name=\"btnSubmit\" value=\"商品詳細\">\n");
      out.write("                </tr>\n");
      out.write("            </table>\n");
      out.write("        </form>\n");
      out.write("        ");
 } 
      out.write("\n");
      out.write("    </body>\n");
      out.write("</html>\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
