import FlightDetails from "../components/FlightDetails";
import { useData } from "../hooks/useData";

const DetailsPage = () => {
  const { myflight } = useData();
  return <>{myflight && <FlightDetails flight={myflight} />}</>;
};

export default DetailsPage;
