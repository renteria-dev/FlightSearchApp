import { Duration } from "luxon";
import { Segment } from "../interfaces/ResponseFlights";

export  function parseISO8601Duration(duration: string): string {
    
    const parsedDuration = Duration.fromISO(duration);

    if (!parsedDuration.isValid) {
      throw new Error("Invalid duration format");
    }
  
    const days = parsedDuration.days;
    const hours = parsedDuration.hours;
    const minutes = parsedDuration.minutes;
    let result = "";

    if (days > 0) {
      result += `${days} ${days === 1 ? "day" : "days"}`;
    }

    if (hours > 0) {
      if (result) result += " ";
      result += `${hours} ${hours === 1 ? "hour" : "hours"}`;
    }

    if (minutes > 0) {
      if (result) result += " ";
      result += `${minutes} ${minutes === 1 ? "minute" : "minutes"}`;
    }

    if (!result) {
      result = "0 minutes";
    }

    return result;
  }

export  const getLayoverTime = (segments: Segment[]) => {
    return segments
      .map((segment, index) => {
        if (index < segments.length - 1) {
          const departure = new Date(segments[index + 1].departure.at);
          const arrival = new Date(segment.arrival.at);
          const layover = new Date(departure.getTime() - arrival.getTime());
          return `${layover.getUTCHours()}h ${layover.getUTCMinutes()}m in
          ${segment.arrival.iataCode} 
          `;
        }
        return null;
      })
      .filter(Boolean);
  };