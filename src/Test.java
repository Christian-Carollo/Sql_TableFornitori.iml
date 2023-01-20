import java.sql.*;

public class StampaProdottiFornitore {

    public static void main(String[] args)throws SQLException{

        Connection connection = null;

        ResultSet resultSet;

        DbUtility dbUtility = new DbUtility();

        dbUtility.createFornitoriTable();
        dbUtility.createProdottiTable();
        dbUtility.createCatalogoTable();


        try{

        // Dati di connessione al database
        String url       = "jdbc:mysql://localhost:3306/newdb";
        String user      = "root";
        String password  = "Fumettistica11!";

        connection = DriverManager.getConnection(url, user, password);


        if (connection != null){
            System.out.println("La connessione è stata eseguita " + connection.getCatalog());
        }

        Statement statement = connection.createStatement();


        resultSet = statement.executeQuery("SELECT Fornitori.Nome, Prodotti.Tipo, Prodotti.Marca, Prodotti.Modello, Catalogo.Costo " +
                "FROM Fornitori " +
                "INNER JOIN Catalogo ON Fornitori.CodiceFornitore = Catalogo.CodiceFornitore " +
                "INNER JOIN Prodotti ON Catalogo.CodiceProdotto = Prodotti.CodiceProdotto " +
                "ORDER BY Fornitori.Nome");


        String nomeFornitoreCorrente = "";

        // Stampa dei prodotti forniti per ogni fornitore

            while (resultSet.next()) {
                String nomeFornitore = resultSet.getString("NomeFornitore");
                if (!nomeFornitore.equals(nomeFornitoreCorrente)) {
                    System.out.println("Fornitore: " + nomeFornitore);
                    nomeFornitoreCorrente = nomeFornitore;
                }

                String marca = resultSet.getString("Marca");
                String tipo = resultSet.getString("Tipo");
                String modello = resultSet.getString("Modello");
                double costo = resultSet.getDouble("Costo");

                System.out.println("  Prodotto: " + marca + " " + tipo + " " + modello + " - Costo: " + costo);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try{
                if(connection != null){
                    connection.close();
                };
            }catch(SQLException ex){
                System.out.println(ex.getMessage());
            }
        }
    }
}
