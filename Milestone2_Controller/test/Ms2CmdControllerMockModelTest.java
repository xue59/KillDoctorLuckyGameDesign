import controller.Controller;
import model.world.World;
import org.junit.Before;

public class Ms2CmdControllerMockModelTest {
  private World mockWorld;
  private StringBuilder log;
  private Controller cmdController;

  @Before
  public void setUp() {
    log = new StringBuilder();
    mockWorld = new WorldMockModel(log);
//    cmdController = new CmdControllerImplement();

  }
}
