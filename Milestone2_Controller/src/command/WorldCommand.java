package command;

import model.world.World;

public interface WorldCommand {

  String execute(World model) throws IllegalAccessException;
}
