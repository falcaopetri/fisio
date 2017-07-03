package src

import br.ufscar.dc.fisio.Papel
import br.ufscar.dc.fisio.Usuario
import br.ufscar.dc.fisio.UsuarioPapel

import java.text.DateFormat
import java.text.SimpleDateFormat

final class Util {
    public static final DateFormat df = new SimpleDateFormat("dd/MM/yyyy")

    public static void savePapel(Usuario user) {
        def papel = Papel.findByAuthority(user.AUTHORITY) ?:
                new Papel(authority: user.AUTHORITY).save()

        UsuarioPapel.create(user, papel)
    }
}
