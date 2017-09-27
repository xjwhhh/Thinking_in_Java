package web.servlet;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.io.IOException;
import java.rmi.server.ServerCloneException;

public class VoteCounter extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cookie[] cookies = null;
        int index;
        Cookie newCookie;
        int[] votes = null;
        String vote;
        File votesdat = new File("voteadar.ser");
        String[] candidates = {"Daren Dogman", "Timmy Taildragger", "Don Dogpile"};

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        cookies = request.getCookies();

        vote = request.getParameter("vote");
        //没有选择候选人
        if (vote == null) {
            makeHeader(response, out);
            out.println("You submitted a ballot with no vote marked <br />");
            out.println("Please mark the ballot and resubmit");
        }
        //选择了候选人
        else {
            //之前没有投过票
            if (!votedBefore(cookies)) {
                synchronized (this) {
                    //如果投票数据文件存在，读取数据放到一个当前投票结果数组中
                    if (votesdat.exists()) {
                        ObjectInputStream indat = new ObjectInputStream(new FileInputStream(votesdat));
                        try {
                            votes = (int[]) indat.readObject();
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        }
                    }
                    //否则创建投票结果数组
                    else {
                        votes = new int[3];
                    }
                    //用新选票更新投票结果数组
                    if (vote.equals("Daren Dogman")) {
                        votes[0]++;
                    } else if (vote.equals("Timmy Taildragger")) {
                        votes[1]++;
                    } else {
                        votes[2]++;
                    }
                    //把投票结果数组写入硬盘中
                    ObjectOutputStream outdat = new ObjectOutputStream(new FileOutputStream(votesdat));
                    outdat.writeObject(votes);
                    outdat.flush();
                    outdat.close();
                }

                //生成一个“iVoted”cookie并添加到响应中
                newCookie = new Cookie("iVoted", "true");
                newCookie.setMaxAge(5);
                response.addCookie(newCookie);

                //返回一条消息告诉客户端当前的投票统计结果
                makeHeader(response, out);
                out.println("Your vote has been received");
                out.println("<br /><br />Current Voting Totals:<br />");

                for (index = 0; index < votes.length; index++) {
                    out.println("<br />");
                    out.println(candidates[index]);
                    out.println(": ");
                    out.println(votes[index]);
                }
            }
            //之前投过票
            else {
                makeHeader(response, out);
                out.println("Your vote is illegal - you have already voted!");
            }
        }
        out.print("</body></html>");
        out.close();
    }

    private boolean votedBefore(Cookie[] cookies) {
        if (cookies == null || cookies.length == 0) {
            return false;
        }
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("iVoted")) {
                return true;
            }
        }
        return false;
    }

    private void makeHeader(HttpServletResponse response, PrintWriter out) throws IOException {
        out.println("<html><head>");
        out.println("<title>Return Message</title></head><body>");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }
}
