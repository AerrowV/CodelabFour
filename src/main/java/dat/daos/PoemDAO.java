package dat.daos;

import dat.entities.Poem;

import java.util.List;

public class PoemDAO implements IDAO<Poem, Long>  {


    @Override
    public Poem create(Poem poem) {
        return null;
    }

    @Override
    public Poem read(Long id) {
        return null;
    }

    @Override
    public List<Poem> readAll() {
        return List.of();
    }

    @Override
    public Poem update(Poem poem) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
