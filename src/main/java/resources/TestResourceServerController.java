package resources;

public class TestResourceServerController implements TestResourceServerControllerMBean{

    private TestResourceServer testResourceServer;

    public TestResourceServerController(TestResourceServer testResourceServer) {
        this.testResourceServer = testResourceServer;
    }

    public void setTestResource(TestResource testResource) {
        testResourceServer.setTestResource(testResource);
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public int getAge() {
        return 0;
    }
}
