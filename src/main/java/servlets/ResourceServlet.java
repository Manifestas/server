package servlets;

import resources.TestResource;
import resources.TestResourceServerControllerMBean;
import xml.ReadXMLFileSAX;

import javax.management.MBeanServer;
import javax.management.ObjectName;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.management.ManagementFactory;

public class ResourceServlet extends HttpServlet {

    private TestResourceServerControllerMBean resourceController;

    public ResourceServlet(TestResourceServerControllerMBean resourceController) {
        this.resourceController = resourceController;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getParameter("path");
        TestResource testResource = (TestResource) ReadXMLFileSAX.read(path);
        if (testResource != null) {
            try {
                resourceController.setTestResource(testResource);
                MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
                ObjectName objectName = new ObjectName("Admin:type=ResourceServerController");
                mbs.registerMBean(testResource, objectName);
                resp.setStatus(HttpServletResponse.SC_OK);
            }catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }
}
