package resources;

public class TestResourceServer {

    private TestResource testResource;

    public TestResourceServer(TestResource testResource) {
        this.testResource = testResource;
    }

    public String getName() {
        return testResource.getName();
    }

    public int getAge() {
        return testResource.getAge();
    }

    public void setTestResource(TestResource testResource) {
        this.testResource = testResource;

    }
}
