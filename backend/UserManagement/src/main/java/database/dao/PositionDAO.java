package database.dao;

import java.util.List;

import main.model.Department;
import main.model.Position;

public interface PositionDAO {

	public Position getPositionByName(String name);

	public List<Position> getAllPosition();

	public void createPosition(Position position);

	public void deletePosition(String name);

	public Position getPositionById(String idPosition);

	public void deleteAllPosition();
}
