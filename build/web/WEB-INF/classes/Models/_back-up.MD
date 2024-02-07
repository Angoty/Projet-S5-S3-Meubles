 public double getPriceMeuble() 
        throws Exception
    {
        double price = 0;

        Meuble m = new Meuble();
        Meuble meuble = m.getSpecificMeuble(this.getIdMeuble());

        Materiel[] materiels = meuble.getAllMateriel();
        MeubleQuantityMateriel[] allMeublesQte = this.getAll(MeubleQuantityMateriel.class, null);

        Vector<Integer> arrayQte = new Vector<Integer>();

        for (MeubleQuantityMateriel meub : allMeublesQte) {
            for (Materiel materiel : materiels) {
                if (materiel.getId().equals(meub.getIdMateriel())) 
                { arrayQte.add(meub.getQte()); }
            }
        }

        for (Integer integer : arrayQte) {
            System.out.println(integer);
        }
        
        for (int i = 0; i < materiels.length && i < arrayQte.size(); i++) 
        {
            price += (materiels[i].getUnitPrice() * arrayQte.get(i));
        }
        
        return price;
    }

   public MeubleQuantityMateriel[] getInstancesInPriceRange(double minPrice, double maxPrice) 
        throws Exception 
    {
        MeubleQuantityMateriel[] allInstances = this.getAll(MeubleQuantityMateriel.class, null);
        Vector<MeubleQuantityMateriel> result = new Vector<>();
    
        for (MeubleQuantityMateriel instance : allInstances) {
            double price = instance.getPriceMeuble();
            if (price >= minPrice && price <= maxPrice) {
                result.add(instance);
            }
        }
    
        MeubleQuantityMateriel[] filteredInstances = new MeubleQuantityMateriel[result.size()];
        result.toArray(filteredInstances);
    
        return filteredInstances;
    }   \




    SELECT prix_achat FROM MeublePrixAchat WHERE id_meuble = 'MEU0003'
    1 : 1500.0 2 : 1000.0
    Prix Achat : 500.0 - Prix revient : 2500.0 = -2000.0
    Benef of Canape = -2000.0


    
    SELECT prix_achat FROM MeublePrixAchat WHERE id_meuble = 'MEU0004'
    1 : 1900.0 2 : 1500.0
    Prix Achat : 1000.0 - Prix revient : 3400.0 = -2400.0
    Benef of Armoir = -2400.0