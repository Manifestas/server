package resources;

public class TestResourceServer implements TestResourceServerMBean{

    private TestResource testResource;

    public TestResourceServer(TestResource testResource) {
        this.testResource = testResource;
    }

    @Override
    public String getName() {
        return testResource.getName();
    }

    @Override
    public int getAge() {
        return testResource.getAge();
    }
}
