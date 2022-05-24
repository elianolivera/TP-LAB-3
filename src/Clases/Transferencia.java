package Clases;



    public class Transferencia {
        protected User us;
        protected   User receptor;
        protected  int UUIDtransaccion;
        protected  int cantidadtransac;
        protected  double monto;
        protected Estado estado;

        public Transferencia(User us, User receptor, int UUIDtransaccion, int cantidadtransac, double monto, Estado estado) {
            this.us = us;
            this.receptor = receptor;
            this.UUIDtransaccion = UUIDtransaccion;
            this.cantidadtransac = cantidadtransac;
            this.monto = monto;
            this.estado = estado;
        }

        public Transferencia transferir(Transferencia t1,  User u1 ,  User u2, float monto){
            u1.setSaldo(u1.getSaldo()-monto);
            u2.setSaldo(u2.getSaldo()+monto);
            t1.setCantidadtransac(t1.getCantidadtransac()+1);
            if (t1.getCantidadtransac()>3){
                t1.setEstado(Estado.VALIDADA);
                ///SE PASA AL ARCHIVO DE VALIDADAS
            }

            return t1;
        }

        public  User getReceptor() {
            return receptor;
        }

        public Transferencia setReceptor( User receptor) {
            this.receptor = receptor;
            return this;
        }

        public Estado getEstado() {
            return estado;
        }

        public Transferencia setEstado(Estado estado) {
            this.estado = estado;
            return this;
        }

        public  User getUs() {
            return us;
        }

        public Transferencia setUs( User us) {
            this.us = us;
            return this;
        }

        public int getUUIDtransaccion() {
            return UUIDtransaccion;
        }

        public Transferencia setUUIDtransaccion(int UUIDtransaccion) {
            this.UUIDtransaccion = UUIDtransaccion;
            return this;
        }

        public int getCantidadtransac() {
            return cantidadtransac;
        }

        public Transferencia setCantidadtransac(int cantidadtransac) {
            this.cantidadtransac = cantidadtransac;
            return this;
        }

        public double getMonto() {
            return monto;
        }

        public Transferencia setMonto(double monto) {
            this.monto = monto;
            return this;
        }

        @Override
        public String toString() {
            return "Transferencia{" +
                    "us=" + us.getNombre() +
                    ", receptor=" + receptor.getNombre() +
                    ", UUIDtransaccion=" + UUIDtransaccion +
                    ", cantidadtransac=" + cantidadtransac +
                    ", monto Transferido=" + monto +
                    ", estado=" + estado +
                    '}';
        }
    }


